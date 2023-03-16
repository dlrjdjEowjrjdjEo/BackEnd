package com.medic.controller;

import com.medic.codef.Codef;
import com.medic.dto.*;
import com.medic.jpa.CommonQuestion;
import com.medic.jpa.Like;
import com.medic.jpa.UserEntity;
import com.medic.service.CommonQuestionService;
import com.medic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@RestController
@RequestMapping("/")
public class UserController {

    private final Codef codef;
    private final UserService userService;
    private final CommonQuestionService commonQuestionService;

    @Autowired
    public UserController(Codef codef, UserService userService, CommonQuestionService commonQuestionService) {
        this.codef = codef;
        this.userService = userService;
        this.commonQuestionService = commonQuestionService;
    }

    @GetMapping("/check")
    public String check() {
        return "Hello User Controller";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody String code) throws Exception {
        code = code.substring(16);
        code = code.replace("\"", "").replace("}", "");
        HashMap<String, Object> userInfo = new HashMap<String, Object>();

        String reqUrl = "https://kapi.kakao.com/v2/user/me";
        URL url = new URL(reqUrl);

        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Bearer " + code);
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        JSONParser parser = new JSONParser();
        JSONObject jsonOb = (JSONObject) parser.parse(sb.toString());
        JSONObject kakaoAccount = (JSONObject) jsonOb.get("kakao_account");
        JSONObject getNickname = (JSONObject) kakaoAccount.get("profile");

        String name = (String) getNickname.get("nickname");
        String age = (String) kakaoAccount.get("age_range");
        age = age.substring(0, 2);
        int realAge = Integer.parseInt(age);
        String email = (String) kakaoAccount.get("email");
        String gender = (String) kakaoAccount.get("gender");
        gender = "미정";

        boolean type = userService.signup(email, name, realAge, gender);

        HashMap<String, Object> map = new HashMap();
        map.put("signup", type);
        map.put("name", name);
        map.put("age", realAge);
        map.put("email", email);
        map.put("gender", gender);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/healthcheckdata")
    public ResponseEntity<?> getHealthCheckData(@RequestBody HealthDTO.ReqGet getHealthReq) throws Exception {
        HashMap<String, Object> map = codef.getHealthCheckData(getHealthReq.getUserName(), getHealthReq.getPhoneNumber(), getHealthReq.getBirthday());
        List<String> list = (List<String>) map.get("list");
        boolean check = (boolean) map.get("check");
        UserEntity user = userService.findOneByEmail(getHealthReq.getEmail());
        commonQuestionService.createCq(user.getUserId());
        int userId = user.getUserId();
        for (String str : list) {
//			System.out.println("리스트에 담긴 영양소 값=>"+str);
            if (str.equals("철분")) commonQuestionService.updateFe(userId);
            if (str.equals("비타민 D")) commonQuestionService.updateVitaminD(userId);
            if (str.equals("종합비타민")) commonQuestionService.updateMultivitamin(userId);
            if (str.equals("비타민 C")) commonQuestionService.updateVitaminC(userId);
            if (str.equals("오메가 3")) commonQuestionService.updateOmega3(userId);
            if (str.equals("마그네슘")) commonQuestionService.updateMagnesium(userId);
            if (str.equals("비타민 B")) commonQuestionService.updateVitaminB(userId);
            if (str.equals("콜라겐")) commonQuestionService.updateCollagen(userId);
            Thread.sleep(100);
        }
        userService.updateRecommend(getHealthReq.getEmail(), list.get(0), list.get(1), list.get(2));

        return new ResponseEntity<Boolean>(check, HttpStatus.OK);
    }

    //큰 약 잘 먹는지, 선호하는 브랜드명,
    @PostMapping("/healthcheckdata/detailcheck")
    public ResponseEntity<?> setCommonQuestionForHealthCheck(@RequestBody HealthDTO.ReqDetail detailHealthReq) {
        userService.insertDetail(detailHealthReq);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    //4. 영양소 추천으로 인한 추천성분 업데이트
    @PutMapping("/recommend")
    public ResponseEntity<?> updateRecommend(@RequestBody HealthDTO.ReqGet getHealthReq) throws Exception {
        codef.getHealthCheckData(getHealthReq.getUserName(), getHealthReq.getPhoneNumber(), getHealthReq.getBirthday());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    /*//5. 좋아요 누르면 insert
    @PostMapping("/insertlike")
    public ResponseEntity<?> insertLike(@RequestBody InsertLikeReq insertLikeReq) throws Exception {
        userService.insertLike(insertLikeReq.getUserId(), insertLikeReq.getSupplementId());
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    //6. 좋아요 눌러진거 삭제하기
    @DeleteMapping("/deletelike/{userId}/{supplementId}")
    public ResponseEntity<?> deleteLike(@PathVariable int userId, @PathVariable int supplementId) throws Exception {
        userService.deleteLike(userId, supplementId);
        return new ResponseEntity<String>(HttpStatus.OK);
    }*/

    //7. 영양제별 좋아요 개수 배열 리턴
    @GetMapping("/supplementlike/{supplementId}")
    public ResponseEntity<?> deleteLike(@PathVariable int supplementId) {
        List<Integer> list = userService.likeListOfSupplement(supplementId);
        return new ResponseEntity<List<Integer>>(list, HttpStatus.OK);
    }

    //유저가 누른 좋아요 개수를 통해서 프론트가 필요한 데이터 전달
    @GetMapping("/userLike/{userId}")
    public ResponseEntity<?> getUserLike(@PathVariable int userId) {
        List<Like> list = userService.getUserLike(userId);
        List<HashMap<String, Object>> supList = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        /*for (int i = 0; i < list.size(); i++) {
            Supplement supplement = supplementService.getSupplement(list.get(i).getSupplementId());
            HashMap<String, Object> babyMap = new HashMap<>();
            babyMap.put("image", supplement.getImage());
            babyMap.put("name", supplement.getSupplementName());
            babyMap.put("brand", supplement.getBrand());
            babyMap.put("supplementId", supplement.getSupplementId());
            babyMap.put("like", supplement.getLike());
            supList.add(babyMap);
        }*/

        return new ResponseEntity<List<HashMap<String, Object>>>(supList, HttpStatus.OK);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<?> deleteLike(@PathVariable String email) {
        UserEntity user = userService.findOneByEmail(email);
        return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
    }

    @PutMapping("/user/updateName")
    public ResponseEntity<?> updateName(@RequestBody UpdateNameReq updateNameReq) {
        userService.updateName(updateNameReq);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PutMapping("/user/firstSurvey")
    public ResponseEntity<?> firstSurvey(@RequestBody UserFirstSurveyReq userFirstSurveyReq) {
//		System.out.println("컨트롤러에서의 outdoor =>"+userFirstSurveyReq.getOutdoor_activity());
        String[] arr = userService.userFirstSurvey(userFirstSurveyReq);
        UserEntity userEntity = userService.findOneByUserId(userFirstSurveyReq.getUserId());
        userService.updateRecommend(userEntity.getEmail(), arr[0], arr[1], arr[2]);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

//    @PostMapping("/user/secondSurvey")
//    public ResponseEntity<?> secondSurvey(@RequestBody UserSecondSurveyReq userSecondSurveyReq) throws Exception {
//        List<SupplementAndScoreRes> list = recommendService.recommendSupplement(userSecondSurveyReq);
//        return new ResponseEntity<List<SupplementAndScoreRes>>(list, HttpStatus.OK);
//    }

    //성별 바꿔주자
    @PatchMapping("/user/patchgender/{email}/{gender}")
    public ResponseEntity<?> patchGender(@PathVariable String email, @PathVariable String gender) {
//		System.out.println(email);
//		System.out.println(gender);
        userService.patchGender(email, gender);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @GetMapping("/findCommonQuestion/{userId}")
    public ResponseEntity<?> findCommonQuestion(@PathVariable Integer userId) {
        CommonQuestion data = commonQuestionService.findOneByUserId(userId);

        String disease = "";
        if (data.getAnemia()) disease += "빈혈, ";
        if (data.getThyroidDisease()) disease += "갑상선 질환, ";
        if (data.getKidney_disease()) disease += "신장 질환, ";
        if (data.getDiabetes()) disease += "당뇨병, ";
        if (data.getHighBloodPressure()) disease += "고혈압, ";
        if (data.getHyperlipidemia()) disease += "고지혈증, ";
        if (data.getPeriodontitis()) disease += "치주염, ";
        if (data.getHeartFailure()) disease += "심부전, ";

        String lack = "";
        if (data.getLack().contains("채소")) lack += "채소, ";
        if (data.getLack().contains("생선")) lack += "생선, ";
        if (data.getLack().contains("육류")) lack += "육류, ";
        if (data.getLack().contains("과일")) lack += "과일, ";

        String medicine = "";
        if (data.getContraceptive()) medicine += "피임약, ";
        if (data.getAntacid()) medicine += "제산제, ";
        if (data.getBloodPressureMedicine()) medicine += "혈압약, ";
        if (data.getDiuretic()) medicine += "이뇨제, ";
        if (data.getSotalol()) medicine += "부정맥(소타롤), ";
        if (data.getGabapentin()) medicine += "항경련제(가바펜틴), ";
        if (data.getLevothyroxine()) medicine += "갑상선(레보티록신), ";
        if (data.getAntibiotics()) medicine += "항생제, ";

        Boolean menopause = false;
        String brand = "";
        if (data.getPreferred_brand().contains("뉴트리코어")) brand += "뉴트리코어, ";
        if (data.getPreferred_brand().contains("종근당")) brand += "종근당, ";
        if (data.getPreferred_brand().contains("GC녹십자")) brand += "GC녹십자, ";
        if (data.getPreferred_brand().contains("제일헬스사이언스")) brand += "제일헬스사이언스, ";
        if (data.getPreferred_brand().contains("하루틴")) brand += "하루틴, ";
        if (data.getPreferred_brand().contains("solgar")) brand += "solgar, ";
        if (data.getPreferred_brand().contains("natural factors")) brand += "natural factors, ";
        if (data.getPreferred_brand().contains("Life Extension")) brand += "Life Extension, ";
        if (data.getPreferred_brand().contains("21st Century")) brand += "21st Century, ";
        if (data.getPreferred_brand().contains("Thorne Research")) brand += "Thorne Research, ";
        if (data.getPreferred_brand().contains("NOW Foods")) brand += "NOW Foods, ";
        if (data.getPreferred_brand().contains("MegaFood")) brand += "MegaFood, ";
        if (data.getPreferred_brand().contains("Rainbow Light")) brand += "Rainbow Light, ";
        if (data.getPreferred_brand().contains("Jarrow Formulas")) brand += "Jarrow Formulas, ";
        if (data.getPreferred_brand().contains("Source Naturals")) brand += "Source Naturals, ";

        String problem = "";
        if (data.getProblem().contains("면역력 개선")) problem += "면역력 개선, ";
        if (data.getProblem().contains("암, 심혈관 질환 예방")) problem += "암, 심혈관 질환 예방, ";
        if (data.getProblem().contains("치매 예방")) problem += "치매 예방, ";
        if (data.getProblem().contains("식후 혈당 관리")) problem += "식후 혈당 관리, ";
        if (data.getProblem().contains("콜레스테롤 수치 개선")) problem += "콜레스테롤 수치 개선, ";
        if (data.getProblem().contains("관절 통증")) problem += "관절 통증, ";
        if (data.getProblem().contains("뼈 건강")) problem += "뼈 건강, ";
        if (data.getProblem().contains("간 건강")) problem += "간 건강, ";
        if (data.getProblem().contains("우울감")) problem += "우울감, ";
        if (data.getProblem().contains("PMS, 월경통")) problem += "PMS, 월경통, ";
        if (data.getProblem().contains("빈혈")) problem += "빈혈, ";
        if (data.getProblem().contains("수면")) problem += "수면, ";
        if (data.getProblem().contains("눈 건강")) problem += "눈 건강, ";
        if (data.getProblem().contains("청력 보호")) problem += "청력 보호, ";
        if (data.getProblem().contains("주름 개선")) problem += "주름 개선, ";
        if (data.getProblem().contains("모발 건강")) problem += "모발 건강, ";

        String symptom = "";
        if (data.getHeartburn()) symptom += "속쓰림, ";
        if (data.getConstipation()) symptom += "변비, ";
        if (data.getDiarrhea()) symptom += "설사, ";
        if (data.getDigestiveDisorder()) symptom += "소화장애, ";
        if (data.getMigraine()) symptom += "편두통, ";
        if (data.getBackache()) symptom += "요통, ";
        if (data.getBowelSyndrome()) symptom += "과민성 대장군 증후군, ";
        if (data.getAtopy()) symptom += "아토피 피부염, ";
        if (data.getDandruff()) symptom += "비듬, ";
        if (data.getStomatitis()) symptom += "구내염, ";
        if (data.getLegCramp()) symptom += "야간 다리 경련, ";


        CommonQuestionRes cm = new CommonQuestionRes(
                data.getAllergy(),
                data.getBalanced_meal(),
                disease, data.getDrinking(),
                data.getIs_ok_big_pill(),
                lack,
                medicine,
                menopause,
                data.getOutdoor_activity(),
                brand, data.getPregnant(),
                problem,
                data.getSmoking(),
                symptom,
                data.getPhysicalActivity(),
                data.getUserId());
        return new ResponseEntity<>(cm, HttpStatus.OK);
    }
}
