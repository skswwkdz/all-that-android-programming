package com.appstudio.android.sample.ikeeper_android.common;


public class Constant  {
	public static int CODE_INTRO 			= 100;
	public static int CODE_MAIN_TO_AUTH 	= 200;
	
	public static String AUTH_SMS_MSG 	= "auth_msg";
	public static String C2DM_CLIENT_APPID 	= "c2dm_client";
	public static String IS_AUTH_YN 	= "isAgreeTerms";
	public static String IS_PARENT_MODE 	= "isParent";
	
	public final String VERSION 				= "0.1";
	public final String STATE_SUCCESS 			= "0";
	public static final String TYPE_LOCATION 	= "location";
	public static final String TYPE_USERINFO 	= "userinfo";
	public static final String TYPE_LOCK 		= "lock";
	public static final String IS_LOCK_TRUE		= "3333333333";
	public static final String IS_LOCK_FALSE	= "2222222222";
	public static final String PLATFORM 		= "3";
	public static String GOOGLE_AUTH_KEY 		= "";
	
	
	public static String USER_PHONE_NO 		= "";
	public static String USER_NAME 			= "";
	public static String USER_APPID 		= "";
	public static String USER_OS_TYPE 		= "";
	public static String USER_PAPA 			= "";
	public static String USER_MAMA 			= "";
	public static String USER_RELATION 		= "";
	public static String USER_VERSION 		= "";

	


	
	
	
	
	
	
	private StringBuffer termsStr;
	public String getTerms(){
		return termsStr.toString();
	}
	public Constant() {
		super();
termsStr = new StringBuffer();
		
		termsStr.append("피망 위치기반서비스이용약관\n");
		termsStr.append("제1조 [목적]\n");
		termsStr.append("이 약관은 (주)네오위즈게임즈(이하 “회사”)에서 제공하는 위치기반 서비스와 관련하여 회사와 위치정보주체와의 권리, 의무 및 책임사항, 기타 필요한 사항을 규정함을 목적으로 합니다.\n");

		termsStr.append("제2조 [이용약관의 체결 및 변경]\n");
		termsStr.append("1.이 이용약관은 휴대폰 단말기에 본 약관 제4조의 위치기반서비스가 포함된 개별 어플리케이션을 설치 후 최초 실행 시 고지되며 이에 대하여 이용자가 동의함으로써 효력이 발생합니다. 만약 동의하지 않으시면 “취소” 버튼을 선택할 수 있습니다.\n");
		termsStr.append("2.회사는 “위치정보의 보호 및 이용 등에 관한 법률” “정보통신망 이용촉진 및 정보보호 등에 관한법률” “약관의규제에관한법률”, 등 관련법을 준수하며, 이를 위배하지 않는 범위에서 이 약관을 개정할 수 있습니다.\n");
		termsStr.append("3.회사는 약관을 개정할 경우 그 개정이유 및 적용일자를 명시하여 현행 약관과 함께 적용일자 30일전부터 적용일 전일까지 공지합니다.\n");
		termsStr.append("4.회사가 전항에 따라 개정약관을 공지하면서 이용자에게 공지일로부터 약관 변경 적용일까지 거부의사를 표시하지 않으면 의사표시가 표명된 것으로 본다는 뜻을 공지하였음에도 불구하고 이용자가 명시적으로 거부의사를 표시하지 아니하는 경우 이용자가 개정약관에 동의한 것으로 봅니다.\n");
		termsStr.append("5.이용자는 변경된 약관에 동의하지 아니하는 경우 서비스의 이용을 중단하고 이용계약을 해지할 수 있습니다.\n");
		termsStr.append("제3조 [이용약관의 중지 및 위치정보 이용 동의의 철회]\n");
		termsStr.append("1.위치정보주체는 휴대폰 단말기에 탑재된 위치정보시스템(LBS) 기능을 중지함으로써, 일시적으로 위치정보 제공을 중단할 수 있습니다.\n");
		termsStr.append("2.위치정보주체가 회사가 제공한 위치기반서비스가 포함된 어플리케이션을 삭제하면 자동으로 이용계약이 철회되면서 이에 수반하여 위치정보 이용 동의도 철회 됩니다.\n");
		termsStr.append("3.위치정보주체는 언제든지 이 약관 제10조의 연락처로 회사에 이용계약을 철회 및 위치정보이용 동의의 철회를 요청할 수 있습니다.\n");
		termsStr.append("제4조 [서비스의 내용 및 용도]\n");
		termsStr.append("1.회사는 위치정보사업자가 제공하는 위치정보를 전달 받아 아래와 같은 위치기반서비스를 제공합니다.\n"); 
		termsStr.append("a.피망맞고아이 서비스 \n");
		termsStr.append("i.서비스내용: 지역정보를 기반으로 지역별 피망맞고아이서비스를 이용하는 이용자의 랭킹을 등록 조회하는 서비스\n");
		termsStr.append("ii.서비스방법\n");
		termsStr.append("1.사용자는 게임 종료 시, 현재 플레이어의 위치를 기반으로 지역랭킹에 자동으로 반영됩니다.\n");
		termsStr.append("2.게임 종료 시점에 네트워크 접속이 불가능한 상태에서는 지역랭킹에 반영되지 않습니다.\n");
		termsStr.append("3.랭킹은 시즌별로 관리되며, 시즌의 단위는 운영자에 의해 선정됩니다. 랭킹 조회는 모든 사용자가 가능하며, 지난 시즌(최근 1년)의 랭킹도 조회가 가능합니다.\n");
		termsStr.append("iii.위치정보 조회 및 저장 \n");
		termsStr.append("1.게임 종료 시, 등록될 랭킹의 지역을 알기 위해 위치정보를 조회 전송합니다.\n");
		termsStr.append("2.지역랭킹에 등록되는 경우 이용자의 위치정보 중 지역단위(지역 단위는 서비스 운영 시 변경될 수 있으나, 일반적으로 특별시의 구, 광역시의 구/군, 도의 시/군 수준)만 저장됩니다.\n");
		termsStr.append("b.기타 추가되는 위치기반 서비스\n");
		termsStr.append("제5조 [개인위치정보주체의 권리]\n");
		termsStr.append("1.개인 위치정보주체는 개인위치정보 이용ㆍ제공에 대한 동의의 전부 또는 일부에 대하여 언제든지 철회할 수 있으며, 일시적인 중지를 요구할 수 있습니다.\n"); 
		termsStr.append("2.개인위치정보주체는 회사에 대하여 아래 자료의 열람 및 고지를 요구할 수 있으며, 당해 자료에 오류가 있는 경우에는 그 정정을 요구할 수 있습니다. 이 경우 회사는 이용자 본인확인 절차를 거친 후 요구사항에 응해야 하며 정당한 이유 없이 요구를 거절하지 아니합니다. 단, 정보의 열람 및 고지는 최근 1년 정보까지 가능합니다.\n"); 
		termsStr.append("a.개인위치정보주체에 대한 개인위치정보 이용?제공사실 확인자료\n");
		termsStr.append("b.개인위치정보주체의 개인위치정보가 위치정보의 보호 및 이용 등에 관한 법률 또는 다른 법령의 규정에 의하여 제3자에게 제공된 이유 및 내용\n");
		termsStr.append("3.개인위치정보에 대한 정정요구 시 회사는 정정이 완료되기 전까지 당해 개인위치정보를 이용 또는 제공하지 않습니다. \n");
		termsStr.append("4.회사는 개인위치정보주체가 동의의 전부 또는 일부를 철회한 경우에는 지체 없이 개인위치정보 및 위치정보 이용?제공사실 확인자료를 파기합니다. 단, 동의의 일부를 철회하는 경우에는 철회하는 부분의 개인위치정보 및 위치정보 이용?제공사실 확인자료에 한합니다.\n");
		termsStr.append("5.개인위치정보주체는 위 1, 2항의 권리행사를 위해 이메일을 통해 요구할 수 있습니다.\n");
		termsStr.append("제6조 [법정대리인의 권리]\n");
		termsStr.append("a.회사는 만14세 미만 아동으로부터 개인위치정보를 수집/이용 또는 제공하고자 하는 경우에는 만14세 미만 아동과 그 법정대린인의 동의를 받아야 합니다.\n");
		termsStr.append("b.법정대리인은 만14세 미만 아동의 개인위치정보를 수집/이용 제공에 동의하는 경우 동의유보권, 동의철회권 및 일시중지권, 열람 및 고지요구권을 행사할 수 있습니다.\n");
		termsStr.append("제7조 [위치정보 이용ㆍ제공 사실 확인자료 보유근거 및 보유기간]\n");
		termsStr.append("회사는 랭킹서비스의 공정성과 고객응대를 위해 위치정보 이용 제공사실 확인자료를 자동 기록 보존하며 해당 자료는 1년간 보관합니다.\n");

		termsStr.append("제8조 [개인위치정보 제3자 제공]\n"); 
		termsStr.append("회사는 개인위치정보주체의 개인위치정보를 제3자에게 제공하는 서비스를 제공하지 아니합니다.\n");
		
		termsStr.append("제9조 [책임제한]\n");
		termsStr.append("a.회사는 천재지변 또는 이에 준하는 불가항력으로 인하여 위치기반서비스를 제공할 수 없는 경우에는 서비스 제공에 관한 책임이 면제됩니다.\n");
		termsStr.append("b.회사는 위치정보주체의 귀책사유로 인한 서비스 이용의 장애에 대하여는 책임을 지지 않습니다.\n");
		termsStr.append("c.회사는 무료로 제공되는 위치정보서비스 이용과 관련하여 관련법에 특별한 규정이 없는 한 책임을 지지 않습니다.\n");
		termsStr.append("제10조 [분쟁의 조정]\n");
		termsStr.append("a.회사와 위치정보주체 간 제기된 소송은 대한민국법을 준거법으로 합니다.\n");
		termsStr.append("b.회사와 위치정보주체 간 발생한 분쟁에 관한 소송은 민사소송법 상의 관할법원에 제소합니다.\n");
	}
	
	
 
}