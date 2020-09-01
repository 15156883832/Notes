package test1.Utils;

/*
 * 正则表达式
 * 
 */
public class MatchRules {

	// 手机号码(严格的校验方式-官方)
	public static final String MOBILE_RULE_ONE = "^(175|170|134|135|136|137|138|139|141|147|148|150|151|152|154|157|158|159|178|182|183|184|187|188|130|131|132|145|155|156|171|176|185|186|133|149|153|173|177|180|181|189|199|166|198|172)\\d{8}$";
	// 手机号码（要求首位为1+10位数字）
	public static final String MOBILE_RULE_TWO = "^1[0-9]{10}$";
	// 固话
	public static final String PHONE_RULE_ONE = "^(((\\+\\d{2}-)?0\\d{2,3}(-?)\\d{5,8})|(\\d{7}))$";
	// 手机或者固话
	public static final String MOBILE_PHONE_RULE = "^(1[0-9]{10})|((((\\+\\d{2}-)?0\\d{2,3}(-?)\\d{5,8})|(\\d{7})))$";

	/^(([1-9]+)|([0-9]+\.[0-9]{1,2})|0)$/  金额正则  -new  验证数字格式  非负数 小数点后保留两位

	// 金额（大于0的，包含0）
	public static final String PRICE_RULE_ONE = "^(([1-9][0-9]*)|(([0]\\.\\d{1,2}|[1-9][0-9]*\\.\\d{1,2}))|0)$";
	// 金额（大于0的，不包含0）
	public static final String PRICE_RULE_TWO = "^(([1-9][0-9]*)|([0]\\.\\d{1}[1-9]{1})|([0]\\.[1-9]{1})|([1-9][0-9]*\\.\\d{1,2}))$";
	// 金额（限制两位小数，可正可负可为零）
	public static final String PRICE_RULE_THREE = "^((-?)(([1-9][0-9]*)|(([0]\\.\\d{1,2}|[1-9][0-9]*\\.\\d{1,2}))|0))$";

	// 数字(任意数字组合)
	public static final String NUMBER_RULE_ONE = "^[0-9]+$";
	// 数字(正整数)
	public static final String NUMBER_RULE_TWO = "^[1-9]\\d*$";
	// 数字(0和正整数)
	public static final String NUMBER_RULE_THREE = "^([1-9]\\d*)|0$";

	// 小数(\\d{1,n}),n为需要保留的小数位，不填写默认为无穷大
	public static final String DOUBLE_RULE_ONE = "^(([1-9][0-9]*)|(([0]\\.\\d{1,}|[1-9][0-9]*\\.\\d{1,}))|0)$";
	// 小数（可正可负可为零）
	public static final String DOUBLE_RULE_TWO = "^((-?)(([1-9][0-9]*)|(([0]\\.\\d{1,}|[1-9][0-9]*\\.\\d{1,}))|0))$";

	// 字母或者数字
	public static final String CHAR_OR_NUMBER_RULE = "^[0-9A-Za-z]{1,}$";
	// 字母和数字(8-16位)
	public static final String CHAR_AND_NUMBER_RULE = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";

}
