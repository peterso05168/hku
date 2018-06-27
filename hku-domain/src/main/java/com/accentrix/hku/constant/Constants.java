package com.accentrix.hku.constant;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Constants {
    // Common
    public static final Locale LOCALE_UK = Locale.ENGLISH;
    public static final Locale LOCALE_CHINESE = Locale.TRADITIONAL_CHINESE;

    // Common
    public static final String OTHERS = "others";
    public static final String STATUS_NEW = "New";
    public static final String STATUS_PROCESSING = "Processing";
    public static final String STATUS_DONE = "Done";
    public static final String STATUS_SUBMITTED = "Submitted";
    public static final String STATUS_IN_PROCESS = "In Progress";
    public static final String STATUS_IN_PAYMENT = "In Payment";
    public static final String SELECTED = "selected";

    // Registration
    public static final String ACTIVATE_ACC = "Activate Account";
    public static final String ACTIVATE_ACC_CHI = "激活帳戶";
    public static final String ACTIVATE_ACC_CONT = "Please click the following link to activate your account: ";
    public static final String ACTIVATE_ACC_CONT_CHI = "請點擊以下連結以激活閣下之戶口: ";

    // Personal Particulars
    public static final String BIRTH_WRONG = "Date of birth format is incorrect!";
    public static final String BIRTH_WRONG_CHI = "生日格式輸入錯誤!";
    public static final String CHINA_CARD_NO_FORMAT = "China identity card no format is incorrect.";
    public static final String CHINA_CARD_NO_FORMAT_CHI = "身份證格式不正確。";
    public static final String CARD_NO_FORMAT = "Please fill in either China ID Card No, Passport No, HKID Card No.";
    public static final String CARD_NO_FORMAT_CHI = "";

    // Academic Background
    public static final String _9901 = "9901";
    public static final String ASSOCIATE_DEGREE = "ASSOCIATE";
    public static final String HIGHER_DIPLOMA = "HIGHDIP";
    public static final String BACHELOR_DEGREE = "BACHELOR";
    public static final String ADMISSION_WRONG = "Date of admission to programme format is incorrect!";
    public static final String ADMISSION_WRONG_CHI = "入读日期格式输入错误!";
    public static final String EXPECTED_WRONG = "Expected Date of Graduation format is incorrect!";
    public static final String EXPECTED_WRONG_CHI = "预计毕业日期格式输入错误!";
    public static final String SELECT = "select";
    public static final String PREVIOUS_WRONG = "At least one previous study is required";
    public static final String PREVIOUS_WRONG_CHI = "至少有一条以往的研究记录";
    public static final String PE = "PE";
    public static final String SE = "SE";
    public static final String TPSE = "TPSE";
    public static final List<String> ACAD_TYPE_PROGS = Arrays.asList("Higher Diploma", "Associate Degree",
            "Bachelor's Degree");
    public static final String STUDY_MODE_FT = "FT";
    public static final String STUDY_MODE_PT = "PT";
    public static final String HIGHEST_QUALIFICATION_OTHERS = "Others";

    // Other Qualifications
    public static final String IB = "IB";
    public static final String HKDSE = "HKDSE";
    public static final String HKALE = "HKALE";
    public static final String HKCEE = "HKCEE";
    public static final String IGCSE = "IGCSE";
    public static final String SAT = "SAT";
    public static final String NJCEE = "NJCEE";
    public static final String IBRESULT = "IBRESULT";
    public static final String IB_ACHIEVEDRSLT_MENU = "ibAchievedRsltMenu";
    public static final String IB_PREDICTEDRSLT_MENU = "ibPredictedRsltMenu";
    public static final String STREAM = "STREAM";
    public static final String NJCEE_EXAMPLACECD_MENU = "njceeExamPlaceCdMenu";
    public static final String NJCEE_STREAMCD_MENU = "njceeStreamCdMenu";
    public static final String NJCEE_STREAMOTHERS_INPUT = "njceeStreamOthersInput";
    public static final String IB_SCHOOLCD_INPUT = "ibSchoolCdInput";
    public static final String IB_SESSIONNO_INPUT = "ibSessionNoInput";
    public static final String ET_ACHIEVED_RSLT_MENU = "etAchievedRsltMenu";
    public static final String ET_PREDICTED_RSLT_MENU = "etPredictedRsltMenu";
    public static final String HHH = "HHH";
    public static final String IT = "IT";
    public static final String FIRM_OFFER_RECEIVED = "firm offer received";
    public static final String N_NO_GRADE = "N - No grade";
    public static final String CHINA = "China";
    public static final String SHANGHAI = "Shanghai";
    public static final String ZHEJIANG = "Zhejiang";
    public static final String JIANGSU = "Jiangsu";
    public static final String HAINAN = "Hainan";
    public static final String ARTS = "ARTS";
    public static final String SCI = "SCI";
    public static final List<String> NJCEE_JS_SUBJECTS = Arrays.asList("History", "Politics", "Geography", "Physics",
            "Chemistry", "Biology");
    public static final String FILL_SUBJECT_WRONG = "Please fill in the all the subjects according to your results.";
    public static final String FILL_SUBJECT_WRONG_CHI = "請填上報考的所有科目";
    public static final String COMPULSORY = "Compulsory";
    public static final String ELECTIVE = "Elective";
    public static final String HISTORY = "History";
    public static final String PHYSICS = "Physics";
    public static final String YES = "yes";
    public static final String NO = "no";
    public static final String CHANGE_PROVINCE = "changeProvince";
    public static final String CHANGE_STREAM = "changeStream";
    public static final String CHANGE_PROVINCE_MSG = "Are you sure to change province?";
    public static final String CHANGE_STREAM_MSG = "Are you sure to change stream?";
    public static final String INVALID_SCORE_WRONG = " score entered is invalid";
    public static final String INVALID_SCORE_WRONG_CHI = "有科目成绩无效";
    public static final List<String> COMM_ARTS_SUBJECTS = Arrays.asList("Chinese", "English", "Mathematics",
            "Integrated Arts");
    public static final List<String> HIANAN_ARTS_SUBJECTS = Arrays.asList("Chinese", "English", "Mathematics",
            "History", "Politics", "Geography");
    public static final List<String> JIANGSU_ARTS_SUBJECTS = Arrays.asList("Chinese", "English", "Mathematics",
            "History", "Politics", "Geography");
    public static final List<String> COMM_SCI_SUBJECTS = Arrays.asList("Chinese", "English", "Mathematics",
            "Integrated Science");
    public static final List<String> HAINAN_SCI_SUBJECTS = Arrays.asList("Chinese", "English", "Mathematics", "Physics",
            "Chemistry", "Biology");
    public static final List<String> JIANGSU_SCI_SUBJECTS = Arrays.asList("Chinese", "English", "Mathematics",
            "Physics", "Chemistry", "Biology");
    public static final List<String> SHANGHAI_OTHERS_SUBJECTS = Arrays.asList("Chinese", "English", "Mathematics",
            "History", "Politics", "Geography", "Physics", "Chemistry", "Biology");
    public static final List<String> ZHEJIANG_OTHERS_SUBJECTS = Arrays.asList("Chinese", "English", "Mathematics",
            "History", "Politics", "Geography", "Physics", "Chemistry", "Biology", "Techology");
    public static final String EETOK = "EETOK";
    public static final String ALL_GCE = "ALLGCE";
    public static final String EDIT_NJCEE_SUBJECT_MSG = "Please choose exam place and stream first";
    public static final String EDIT_IB_SUBJECT_MSG = "Please choose your best six subjects";
    public static final List<String> EXAM_IB_CODES = Arrays.asList("IBD", "IBC");
    public static final List<String> EXAM_GCE_CODES = Arrays.asList("CPU", "GCE", "IAL", "IGCSE", "GCSE", "SGCEA",
            "GCEO", "SGCEO");
    public static final List<String> EXAM_HHH_CODES = Arrays.asList("HKALE", "HKDSE", "HKCEE");
    public static final List<String> EXAM_GCE_GRADE_LEVELS = Arrays.asList("CPU", "GCE", "IAL", "SGCEA");
    public static final List<String> EXAM_SAT_CODES = Arrays.asList("AP", "RSATI", "RSATII", "SATI", "SATII");
    public static final List<String> EXAM_IT_CODES = Arrays.asList("IELTSA", "IELTSG", "TOEFLPT", "TOEFLIT");
    public static final String TOEFL_PT = "TOEFLPT";
    public static final String TOEFL_IT = "TOEFLIT";
    public static final String TOEFL_NO_INPUT = "toeflNoInput";
    public static final List<String> EXAM_NJCEE_CODES = Arrays.asList("NJCEE", "HMTNJCEE");
    public static final String HMT_NJCEE = "HMTNJCEE";
    public static final List<String> HMT_ARTS_SUBJECTS = Arrays.asList("Chinese", "English", "Mathematics", "History",
            "Geography");
    public static final List<String> HMT_SCI_SUBJECTS = Arrays.asList("Chinese", "English", "Mathematics", "Physics",
            "Chemistry");
    public static final List<String> HMT_NJCEE_EXAM_PLACES = Arrays.asList("Hong Kong", "Macau", "Taiwan");
    public static final String HMT_NJCEE_EXAMPLACECD_MENU = "hmtNjceeExamPlaceCdMenu";
    public static final String EXAM_IBD = "IBD";
    public static final String INVALID_TOTAL_SCORE_WRONG = "NJCEE Total score entered is invalid";
    public static final String INVALID_TOTAL_SCORE_WRONG_CHI = "总成绩无效";
    public static final String REQUIRED_TOTAL_SCORE_WRONG = "NJCEE Total score is required";
    public static final String REQUIRED_TOTAL_SCORE_WRONG_CHI = "请填上总成绩";
    public static final String NJCEE_TABLE = "NJCEETable";
    public static final String TOTAL_SCORE = "totalScore";

    // Choice of Curriculum
    public static final String SPORT = "SPORT";
    public static final String MUSIC = "MUSIC";
    public static final String HOUSING_MANAGEMENT = "Housing Management";
    public static final String PERIOD_FROM_WRONG = "Period From format is incorrect!";
    public static final String PERIOD_FROM_WRONG_CHI = "周期开始时间格式输入错误！";
    public static final String PERIOD_TO_WRONG = "Period To format is incorrect!";
    public static final String PERIOD_TO_WRONG_CHI = "周期结束时间格式输入错误！";
    public static final String AWARD_WRONG = "Date of Award format is incorrect!";
    public static final String AWARD_WRONG_CHI = "颁发日期格式输入错误！";
    public static final String BACHELOR_OF_ARTS = "Bachelor of Arts";
    public static final String PENDING_APPROVAL = "Pending Approval";
    public static final String PA = "PA";
    public static final String OFFER_APPROVED = "Offer Approved";
    public static final String OFFER_APPROVED_CODE = "OA";
    public static final String OA = "OA";
    public static final String CONFIRM_OFFER_APPROVED = "Confirm Offer Approved";
    public static final String CONFIRM_OFFER_APPROVED_CODE = "COA";
    public static final String PENDING_WITHDRAW_APPROVAL = "Pending Withdraw Approval";
    public static final String PWA = "PWA";
    public static final String WITHDRAW = "Withdraw";
    public static final String CHOICESTATUS = "CHOICESTATUS";
    public static final String HE_FOR_SHE = "HeForShe IMPACT Champion Scholarships";
    public static final String UWC = "HKU-United World College Diversity Scholarships";
    public static final String NIGERIAN = "Hon Ping Entrance Scholarship for Nigerian Students";
    public static final String VTP = "Vietnam Van Thinh Phat Scholarship";
    public static final String AFL = "Asian Future Leaders Scholarship Programme";
    public static final String HKSAR_GSFT = "HKSAR Government Scholarship Fund Targeted Scholarship";
    public static final String HKSAR_GSFT_BRS = "HKSAR Government Scholarship Fund Targeted Scholarship – Belt and Road Scholarships";
    public static final List<String> HE_FOR_SHE_COUNTRYS = Arrays.asList("Afghanistan", "Algeria", "Angola", "Benin",
            "Bhutan", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Central African Republic", "Chad", "Comoros",
            "Congo", "Côte d'Ivoire", "Djibouti", "Egypt", "Eritrea", "Ethiopia", "Gambia", "Ghana", "Guinea",
            "Guinea-Bissau", "Haiti", "India", "Iran, Islamic Republic of", "Iraq", "Jordan", "Kenya",
            "Lao People's Democratic Republic", "Lebanon", "Lesotho", "Liberia", "Madagascar", "Malawi", "Mali",
            "Mauritania", "Morocco", "Mozambique", "Myanmar", "Nepal", "Niger", "Nigeria", "Pakistan",
            "Palestine, State of", "Papua New Guinea", "Rwanda", "Sao Tome and Principe", "Senegal", "Sierra Leone",
            "Solomon Islands", "South Sudan", "Sudan", "Swaziland", "Syrian Arab Republic",
            "Tanzania, United Republic of", " Timor-Leste", "Togo", "Tunisia", "Uganda", "Yemen", "Zimbabwe");
    public static final List<String> UWC_INSTITUTION_CODES = Arrays.asList("BOS1755", "CHI2286", "COS1117", "LPCUWC",
            "NET2348", "SIN321", "SIN2209", "SWA1757", "UK611", "USA700", "IND161", "NOR259", "CAN52", "JAP2522",
            "ITA2496");
    public static final List<String> AFL_COUNTRYS = Arrays.asList("China", "Macao", "Taiwan", "Japan", "Korea",
            "Hong Kong");
    public static final List<String> HKSAR_GSFT_COUNTRYS = Arrays.asList("Brunei", "Cambodia", "Laos", "Myanmar",
            "Philippines", "Singapore", "Vietnam", "India", "Korea");
    public static final List<String> HKSAR_GSFT_BR_COUNTRYS = Arrays.asList("Indonesia", "Malaysia", "Thailand");
    public static final String NIGERIA = "Nigeria";
    public static final String VIET_NAM = "Viet Nam";
    public static final String BACHELOR_OF_NURSING = "Bachelor of Nursing";
    public static final String N = "N";
    public static final String O = "O";
    public static final String CHOICE_SUBMITTED = "SUBMITTED";
    public static final String CHOICE_SHORTLISTED = "SHORTLISTED";
    public static final String CHOICE_OFFERED = "OFFERED";
    public static final String CHOICE_WAITLIST = "WAITLIST";
    public static final String CHOICE_REJECTED = "REJECTED";
    public static final String CHOICE_SELECTED_FOR_INTERVIEW = "SFI";
    public static final String JUPAS = "Joint University Programmes Admissions System (JUPAS)";
    public static final String IAS_OR_NON = "International/Non-JUPAS Admissions Scheme (IAS/Non-JUPAS)";
    public static final String DAS = "Direct Admissions Scheme to Senior Year Places (DAS)";
    public static final String MAINLAND = "Mainland Undergraduate Admissions Scheme (Mainland)";
    public static final String HKNT = "Hong Kong National Team";
    public static final String HKJS = "Hong Kong Junior Squad";
    public static final String IPRC = "Inter-port or Regional Competitions";
    public static final String PART_TIME = "Part-time";
    public static final List<String> HIGHEST_QUALIFICATION_CD = Arrays.asList("HD", "ASD");
    public static final List<String> NOT_POST_SECONDARY_STUDIES_QUALIFICATION = Arrays.asList("FORM5", "FORM6",
            "FORM7");

    // Experience and Achievements
    public static final String EXP_AND_ACHI_CATEGORY = "EAACATEGORY";
    public static final String FROM_WRONG = "From format is incorrect!";
    public static final String FROM_WRONG_CHI = "起始时间格式输入错误!";
    public static final String TO_WRONG = "To format is incorrect!";
    public static final String TO_WRONG_CHI = "终止时间格式输入错误!";
    public static final String LEADERSHIP = "LEADERSHIP";
    public static final String COMM = "COMM";
    public static final String GM = "GM";
    public static final String AAOS = "AAOS";
    public static final String EXTRA_OTHERS = "OTHERS";

    // Reference
    public static final String COUNSELOR = "COUNSELOR";
    public static final List<String> EXAM_NOT_IT_CODES = Arrays.asList("IBC", "HKDSE", "IGCSE", "IBD", "SGCEO", "NJCEE",
            "CPU", "GCE", "IAL", "GCSE", "SGCEA", "GCEO", "HMTNJCEE", "AP", "SATI", "SATII", "RSATI", "RSATII");

    // Reference Code Table
    public static final String HIGHEST_QUALIFICATION = "HQUALIFICATION";
    public static final String PROGRAMME_TYPE = "PROGRAMMETYPE";
    public static final String STUDY_MODE = "STUDYMODE";
    public static final String LEVEL_ATTAINED = "LEVELATTAINED";
    public static final String REFEREE_RELATIONSHIP = "REFRELATION";
    public static final String FACULTY = "Faculty";

    // others
    public static final String DISABILITYTYPE = "DISABILITYTYPE";
    public static final String PERSONAL_PARTICULARS_WRONG = "Personal Particulars is required before submission.";
    public static final String PERSONAL_PARTICULARS_WRONG_CHI = "請填寫个人资料後再次提交.";
    public static final String ACADEMIC_PROFILE_WRONG = "Academic Profile is required before submission.";
    public static final String ACADEMIC_PROFILE_WRONG_CHI = "請填寫教育经历後再次提交.";
    public static final String CHOICE_OF_PROGRAMME_WRONG = "Choice of Programme is required before submission.";
    public static final String CHOICE_OF_PROGRAMME_WRONG_CHI = "請选择课程後再次提交.";

    // Validation Constant
    public static final String WRONG_EXISTED_QUALIFICATION = "The same qualification already existed";
    public static final String WRONG_EXISTED_QUALIFICATION_CHI = "該考試成績已存在";

    // registration
    public static final String ACCOUNT_EMAIL_CHI = "此賬號已註冊，請使用其他賬號。";
    public static final String ACCOUNT_EMAIL = "This account has been registered,Please use other accounts.";
    public static final String ACCOUNT_EMAIL_FORMAT_CHI = "電子郵件格式不正確。";
    public static final String ACCOUNT_EMAIL_FORMAT = "Email format is incorrect.";
    public static final String PASSWORD_FORMAT_CHI = "密碼格式不正確。";
    public static final String PASSWORD_FORMAT = "Password format is incorrect.";

    // login
    public static final String LOGIN_ACCOUNT_CHI = "賬戶或密碼錯誤。";
    public static final String LOGIN_ACCOUNT = "account or password incorrect.";
    public static final String LOGIN_ACCOUNT_NOTNULL_CHI = "請輸入有效的電子郵件地址。";
    public static final String LOGIN_ACCOUNT_NOTNULL = "Please enter valid Registered Email Address.";
    public static final String LOGIN_PASSWORD_NOTNULL_CHI = "請輸入有效密碼。";
    public static final String LOGIN_PASSWORD_NOTNULL = "Please enter valid Password.";
    public static final String LOGIN_CONFIRM_PASSWORD_NOTNULL_CHI = "請輸入有效確認密碼。";
    public static final String LOGIN_CONFIRM_PASSWORD_NOTNULL = "Please enter valid confirm Password.";

    // forgot password
    public static final String RESET_PASSWORD = "Reset Password";
    public static final String RESET_PASSWORD_CHI = "重置密碼";
    public static final String RESET_PASSWORD_CONT = "Please click the following link to reset your account: ";
    public static final String RESET_PASSWORD_CONT_CHI = "請點擊以下連結以重置閣下之戶口: ";

    public static final String FORGOT_PASSWORD_EMAIL = "The account number does not exist, please reenter it.";
    public static final String FORGOT_PASSWORD_EMAIL_CHI = "賬號不存在，請重新輸入。";

    // reset passord
    public static final String RESET_PASSWORD_EQUALS = "Inconsistency Of Ciphers.";
    public static final String RESET_PASSWORD_EQUALS_CHI = "密碼不一致。";
    public static final String RESET_PASSWORD_FAULURE = "Reset password failure.";
    public static final String RESET_PASSWORD_FAULURE_CHI = "重置密碼失敗。";

    public static final String REGISTRATION_TEXT_LANGUAGE = "Please enter English.";
    public static final String REGISTRATION_TEXT_LANGUAGE_CHI = "請輸入英文。";
    public static final String REGISTRATION_ACCOUNT_NO = "The reserve account cannot be the same as the registered account.";
    public static final String REGISTRATION_ACCOUNT_NO_CHI = "備用賬戶不能與註冊賬戶相同。";

    // activation account
    public static final String ACTIVATION_ACCOUNT = "The account is activated successfully, please log in.";
    public static final String ACTIVATION_ACCOUNT_CHI = "賬戶激活成功，請登錄。";
    public static final String ACTIVATION_ACCOUNT_FAULURE = "The account is activated failure.";
    public static final String ACTIVATION_ACCOUNT_FAULURE_CHI = "賬戶激活失敗。";

    // admission exercise
    public static final String ADMISSION_YEAR_EXIST = "admission year already exist";
    public static final String ADMISSION_YEAR_EXIST_CHI = "招生年份已经存在";
    public static final String START_DATE_WRONG = "Application start date format is incorrect!For Example:01-01-1900";
    public static final String START_DATE_WRONG_CHI = "申请开始日期格式输入错误!例如：01-01-1900";
    public static final String END_DATE_WRONG = "Application end date format is incorrect!For Example:01-01-1900";
    public static final String END_DATE_WRONG_CHI = "申请结束日期格式输入错误!例如：01-01-1900";
    public static final String PROG_CHOICE_END_DATE_WRONG = "Programme choice end date format is incorrect!For Example:01-01-1900";
    public static final String PROG_CHOICE_END_DATE_WRONG_CHI = "课程结束结束日期格式输入错误!例如：01-01-1900";
    public static final String ADMISSION_FORM = "Admission Form  ";
    public static final String MF_EXCELLENT_SCHE_END_DATE_WRONG = "Multi-faceted excellence scheme end date format is incorrect!For Example:01-01-1900";

    // admin requirement
    public static final String EREQUIREMENT = "EREQUIREMENT";
    public static final String COMPOSITE = "Composite";
    public static final String PUBLICEXAM = "Public Examination";
    public static final String GPS = "GPS";
    public static final String AGE = "Age";
    public static final String REQUIREMENT_NAME_EXISTS = "The Requirement Name already exists.";
    public static final String REQUIREMENT_NAME_EXISTS_CHI = "入學要求名稱已經存在.";
    public static final String SUBSIDIARY_REQUIREMENT_MISSING = "Requirement List must have at least two record.";
    public static final String SUBSIDIARY_REQUIREMENT_MISSING_CHI = "入學要求列表至少要有兩條記錄。";
    public static final String SUBJECT_REQUIREMENT_MISSING = "Please add at least one Subject Requirement.";
    public static final String SUBJECT_REQUIREMENT_MISSING_CHI = "請添加至少一條學科要求。";

    // Programme Admission Controls
    public static final String LVLOFSTUDY = "LVLOFSTUDY";
    public static final String NO_REQUIREMENT_FORMULA_RECORD = "Please add at least one Entrance Requirement or Scoring Formula.";
    public static final String NO_REQUIREMENT_FORMULA_RECORD_CHI = "請添加至少一條 Entrance Requirement 或者 Scoring Formula。";
    public static final String NO_GPA_FORMULA_ITEM_RECORD = "Please add at least one GPA Formula Item.";
    public static final String NO_GPA_FORMULA_ITEM_RECORD_CHI = "請添加至少一條GPA Formula Item。";

    // Application
    public static final String APPNO = "APPNO";
    public static final String RED = "red";
    public static final String GREEN = "green";
    public static final String BLUE = "blue";

    // Announcement
    public static final String ANNCMNT_TYPE = "ANNCMNT_TYPE";
    public static final String INTERVIEW_CD = "INTERVIEW";
    public static final String OFFERACCEPT_CD = "OFFERACCEPT";
    public static final String SCHOLARSHIP_CD = "SCHOLARSHIP";
    public static final String PAYMENT_FAIL_CD = "PAYMENT_FAIL";
    public static final String PAYMENT_SUCCESS_CD = "PAYMENT_SUCCESS";
    public static final String APPACKNOWLEDGE_CD = "APPACKNOWLEDGE";
    public static final String INTERVIEW_VALUE = "Interview";
    public static final String OFFERACCEPT_VALUE = "Offer Acceptance";
    public static final String SCHOLARSHIP_VALUE = "Scholarship Acceptance";
    public static final String PAYMENT_FAIL_VALUE = "Proceed to make payment";
    public static final String PAYMENT_SUCCESS_VALUE = "Payment Acknowledgement";
    public static final String APPACKNOWLEDGE_VALUE = "Accplicant Acknowledgement";
    public static final String ANNCMNT_STATUS_NEW = "statusNew";
    public static final String ANNCMNT_STATUS_UPDATED = "statusUpdated";
    public static final String ANNCMNT_STATUS_URGENT = "statusUrgent";
    public static final String ANNCMNT_STATUS_PROCESSING = "statusProcessing";
    public static final String APPLICATION_NO = "Application No";
    public static final String WELCOME_CD = "WELCOME";
    public static final String WELCOME_VALUE = "Welcome";

    // supporting document
    public static final String WAITING_FOR_UPLOAD = "waitingforupload";
    public static final String REVIEWING = "reviewing";
    public static final String REJECTED = "rejected";
    public static final String VERIFIED = "verified";
    public static final String UPLOAD = "upload";
    public static final String FILE_IS_NULL = "The file is null.";
    public static final String NO_FILE_IS_SELECTED = "No file is selected.";
    public static final String FILE_IS_NULL_CHI = "文件為空！";
    public static final String UNSUPPORTED_FILE_FORMAT = "Unsupported file format(s), the file(s) should be in doc, docx, xls, xlsx, ppt, pptx, gif, jpg or pdf format.";
    public static final String UNSUPPORTED_FILE_FORMAT_CHI = "不支持的文件格式，文件格式應為 doc，docx，xls，xlsx，ppt，pptx，gif，jpg 或者 pdf。";
    public static final String UNSUPPORTED_FILE_SIZE = "Unsupported file size, each file should not exceed 10MB.";
    public static final String UNSUPPORTED_FILE_SIZE_CHI = "不支持的文件大小，每個文件不應超過 10MB。";
    public static final Long SUPPORTED_UPLOAD_FILE_SIZE = 10485760l;
    public static final String DOC_TYPE_PIS = "PIS";
    public static final String DOC_TYPE_OTHERS = "OTHERS";
    public static final String DOC_CD_PI = "PI";
    public static final String DOC_CD_PS = "PS";
    public static final String DOC_CD_VISA = "VISA";
    public static final String HKU_UCL_DUAL_DEGREE_PROG_CD = "6406DD";

    // My Profile
    public static final String TYPE_FORM = "FORM";
    public static final String SAVED_MY_PROFILE = "Saved My Profile";
    public static final String SAVED_PERSONAL_PARTICULARS = "Saved Application Form - Personal Particulars";
    public static final String SAVED_ACADEMIC_PROFILE = "Saved Application Form - Academic Profile";
    public static final String SAVED_ACADEMIC_QUALIFICATIONS = "Saved Application Form - Academic Qualifications";
    public static final String SAVED_CHOICE_OF_PROGRAMME = "Saved Application Form - Choice of Programme";
    public static final String SAVED_ACHIEVEMENTS = "Saved Application Form - Extra-curricular Achievements";
    public static final String SAVED_REFERENCE = "Saved Application Form - Reference";
    public static final String SAVED_OTHERS = "Saved Application Form - Other(s)";

    // Audit Log Approval
    public static final String REMOVE_QUALIFICATION = "Remove Qualification";
    public static final String REMOVE_PROGRAMME_CHOICE = "Remove Programme Choice";

    // Offer status
    public static final String WITHDRAWN_ACCEPTANCE_REASON = "WDAR";
    public static final String PROG_CHOICE_OFFERED = "Offered";
    public static final String PROG_CHOICE_OFFERED_CD = "OFFERED";
    public static final String PROG_CHOICE_OFFERACC = "Offer Accepted";
    public static final String PROG_CHOICE_OFFERACC_CD = "OFFERACC";
    public static final String PROG_CHOICE_OC = "Offer Confirmed";
    public static final String PROG_CHOICE_OC_CD = "OC";
    public static final String PROG_CHOICE_OW = "Offer Withdrawn";
    public static final String PROG_CHOICE_OW_CD = "OW";
    public static final String PROG_CHOICE_ASC = "Application Submitted for Consideration";
    public static final String PROG_CHOICE_ASC_CD = "ASC";
    public static final String PROG_CHOICE_OR = "Offer Rejected";
    public static final String PROG_CHOICE_OR_CD = "OR";
    public static final String PROG_CHOICE_OE = "Offer Expired";
    public static final String PROG_CHOICE_OE_CD = "OE";
    public static final String PROG_CHOICE_OFFER_ACCEPT = "accept";
    public static final String PROG_CHOICE_OFFER_REJECT = "reject";
    public static final String PROG_CHOICE_OFFERASS = "Offer Assigned";
    public static final String PROG_CHOICE_OFFERASS_CD = "OFFERASS";
    public static final String OFFER_ISSUED = "OI";
    public static final String READY_FOR_OFFER = "Ready for Offer";
    public static final String READY_FOR_OFFER_CODE = "RFO";
    public static final String OFFER_ASSIGNED_FOR_APPROVAL = "Offer Assigned for Approval";
    public static final String OFFER_ASSIGNED_FOR_APPROVAL_CODE = "OAFA";

    // Shortlisting
    public static final String NO_RECORD_SELECTED = "No record selected.";
    public static final String NO_RECORD_SELECTED_CHI = "未選中記錄.";

    // Offer Process
    public static final String FIRM = "Firm";
    public static final String CONDITION_TYPE = "CONDITIONTYPE";
    public static final String CONDITION = "Condition";
    public static final String OP_QUALIFICATION = "OPQUALIFICATION";

    public static final String FIRM_OFFER = "FIRMOFFER";
    public static final String FIRM_OFFER_CODE = "Firm Offer";
    public static final String CONDITIONAL_OFFER = "CONDITIONALOFFER";
    public static final String CONDITIONAL_OFFER_CODE = "Conditiona Offer";
}
