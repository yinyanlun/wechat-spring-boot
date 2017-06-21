package com.yin.wechat.web;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yin.wechat.model.UserInfo;
import com.yin.wechat.service.RsOrderService;
import com.yin.wechat.service.UserInfoService;
import com.yin.wechat.utils.HttpRequestParam;

@RestController
@RequestMapping("dashboard")
public class DashboardController{
	@Autowired
	private RsOrderService rsOrderService;
	@Autowired
	private UserInfoService userInfoService;
	
@RequestMapping("")
public Map<String,Object> index(HttpServletRequest request,HttpResponse response) throws ParseException{
	Map<String,Object> dashboardMap=new HashMap<>();
	Subject currentUser = SecurityUtils.getSubject(); 
	Session session = currentUser.getSession();
	UserInfo userInfo= (UserInfo) session.getAttribute("userinfo");
	System.out.println("当前登录信息:"+userInfo);
	//******************************华丽的分割线（交易总额）****************************************
    double alipaySum=0.00;//支付宝交易总额
	double weixinpaySum=0.00;//微信交易总额
	int alipayCount=0;//支付宝交易笔数
	int weixinpayCount=0;//微信交易笔数
	try {
		weixinpaySum=rsOrderService.getPaySum("0","1%");
	} catch (Exception e) {
		weixinpaySum=0.00;
	}
	try {
		alipaySum=rsOrderService.getPaySum("0","2%");
	} catch (Exception e) {
		alipaySum=0.00;
	}
	try {
		alipayCount=rsOrderService.getPayCount("0","2%");
	} catch (Exception e) {
		alipayCount=0;
	}
	try {
		weixinpayCount=rsOrderService.getPayCount("0","1%");
	} catch (Exception e) {
		weixinpayCount=0;
	}
	dashboardMap.put("alipaySum", alipaySum);
	dashboardMap.put("weixinpaySum", weixinpaySum);
	dashboardMap.put("alipayCount", alipayCount);
	dashboardMap.put("weixinpayCount", weixinpayCount);
	//*********************************华丽的分割线（统计图数据:最近5天交易总额）********************************************
	
	//微信
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 String date = sdf.format((new Date()));
	 
	 long dateNow=sdf.parse(date).getTime();//获取当前时间毫秒数
	 dashboardMap.put("searchData", sdf.format((new Date(dateNow-86400000))));
	 double day_1_weixinpay=0;
	 double day_2_weixinpay=0;
	 double day_3_weixinpay=0;
	 double day_4_weixinpay=0;
	 double day_5_weixinpay=0;
	 double day_6_weixinpay=0;
	 double day_7_weixinpay=0;
	 try {
		 day_1_weixinpay=rsOrderService.getPaysSumByBetweenDatetime("0","1%",new Date(dateNow-86400000),new Date(dateNow));

	} catch (Exception e) {
		// TODO: handle exception
	}
	 try {
		 day_2_weixinpay=rsOrderService.getPaysSumByBetweenDatetime("0","1%",new Date(dateNow-86400000*2),new Date(dateNow-86400000));
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	 try {
		 day_3_weixinpay=rsOrderService.getPaysSumByBetweenDatetime("0","1%",new Date(dateNow-86400000*3),new Date(dateNow-86400000*2));

	} catch (Exception e) {
		// TODO: handle exception
	}
	 try {
		 day_4_weixinpay=rsOrderService.getPaysSumByBetweenDatetime("0","1%",new Date(dateNow-86400000*4),new Date(dateNow-86400000*3));

	} catch (Exception e) {
		// TODO: handle exception
	}
	 try {
		 day_5_weixinpay=rsOrderService.getPaysSumByBetweenDatetime("0","1%",new Date(dateNow-86400000*5),new Date(dateNow-86400000*4));
	} catch (Exception e) {
		// TODO: handle exception
	}
	 try {
		 day_6_weixinpay=rsOrderService.getPaysSumByBetweenDatetime("0","1%",new Date(dateNow-86400000*6),new Date(dateNow-86400000*5));
	} catch (Exception e) {
		// TODO: handle exception
	}
	 try {
		 day_7_weixinpay=rsOrderService.getPaysSumByBetweenDatetime("0","1%",new Date(dateNow-86400000*7),new Date(dateNow-86400000*6));
	} catch (Exception e) {
		// TODO: handle exception
	}
	 double weixinpayArray[]={day_1_weixinpay,day_2_weixinpay,day_3_weixinpay,day_4_weixinpay,day_5_weixinpay,day_6_weixinpay,day_7_weixinpay};
	 dashboardMap.put("weixinpayArray", weixinpayArray);
	 //支付宝
	 double day_1_alipay=0;
	 double day_2_alipay=0;
	 double day_3_alipay=0;
	 double day_4_alipay=0;
	 double day_5_alipay=0;
	 double day_6_alipay=0;
	 double day_7_alipay=0;
	 try {
		 day_1_alipay=rsOrderService.getPaysSumByBetweenDatetime("0","2%",new Date(dateNow-86400000),new Date(dateNow));
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	 try {
		 day_2_alipay=rsOrderService.getPaysSumByBetweenDatetime("0","2%",new Date(dateNow-86400000*2),new Date(dateNow-86400000));

	} catch (Exception e) {
		// TODO: handle exception
	}
	 try {
		 day_3_alipay=rsOrderService.getPaysSumByBetweenDatetime("0","2%",new Date(dateNow-86400000*3),new Date(dateNow-86400000*2));

	} catch (Exception e) {
		// TODO: handle exception
	}
	 try {
		 day_4_alipay=rsOrderService.getPaysSumByBetweenDatetime("0","2%",new Date(dateNow-86400000*4),new Date(dateNow-86400000*3));

	} catch (Exception e) {
		// TODO: handle exception
	}
	 try {
		 day_5_alipay=rsOrderService.getPaysSumByBetweenDatetime("0","2%",new Date(dateNow-86400000*5),new Date(dateNow-86400000*4));
	} catch (Exception e) {
		// TODO: handle exception
	}
	 try {
		 day_6_alipay=rsOrderService.getPaysSumByBetweenDatetime("0","2%",new Date(dateNow-86400000*6),new Date(dateNow-86400000*5));
	} catch (Exception e) {
		// TODO: handle exception
	}
	 try {
		 day_7_alipay=rsOrderService.getPaysSumByBetweenDatetime("0","2%",new Date(dateNow-86400000*7),new Date(dateNow-86400000*6));
	} catch (Exception e) {
		// TODO: handle exception
	}
	 double alipayArray[]={day_1_alipay,day_2_alipay,day_3_alipay,day_4_alipay,day_5_alipay,day_6_alipay,day_7_alipay};
	 String dateArrat[]={sdf.format((new Date().getTime())-86400000),sdf.format((new Date().getTime()-86400000*2)),sdf.format((new Date().getTime()-86400000*3)),sdf.format((new Date().getTime()-86400000*4)),sdf.format((new Date().getTime()-86400000*5)),sdf.format((new Date().getTime()-86400000*6)),sdf.format((new Date().getTime()-86400000*7))};
	 dashboardMap.put("alipayArray", alipayArray);
	 dashboardMap.put("dateArrat", dateArrat);
		//*********************************(单日个管理员交易统计)********************************************
	 List<String> userNameList=userInfoService.getUserNameList();
	 int user_weixinPayCountArray[]=new int[userNameList.size()];//个人微信交易成功订单
	 int user_aliPayCountArray[]=new int[userNameList.size()];//个人支付宝交易成功订单
	 double user_weixinPaySumArray[]=new double[userNameList.size()];//个人微信交易成功订单总额
	 double user_aliPaySumArray[]=new double[userNameList.size()];//个人支付宝交易成功订单总额
	 for(int i=0;i<userNameList.size();i++)
	 {
		 String un=userNameList.get(i);
		 try {
			 user_weixinPayCountArray[i]=rsOrderService.getPayCountByUserNameAndBetweenDatetimeAndTradeno("0","1%",un,new Date(dateNow-86400000),new Date(dateNow));
		} catch (Exception e) {
			user_weixinPayCountArray[i]=0;
		}
		 try {
			 user_aliPayCountArray[i]=rsOrderService.getPayCountByUserNameAndBetweenDatetimeAndTradeno("0","2%",un,new Date(dateNow-86400000),new Date(dateNow));
		} catch (Exception e) {
			user_aliPayCountArray[i]=0;
		}
		 try {
			 user_weixinPaySumArray[i]=rsOrderService.getPaySumByUserNameAndBetweenDatetimeAndTradeno("0","1%",un,new Date(dateNow-86400000),new Date(dateNow));
		} catch (Exception e) {
			 user_weixinPaySumArray[i]=0;
		}
		 try {
			 user_aliPaySumArray[i]=rsOrderService.getPaySumByUserNameAndBetweenDatetimeAndTradeno("0","2%",un,new Date(dateNow-86400000),new Date(dateNow));
		} catch (Exception e) {
			user_aliPaySumArray[i]=0;
		}
		 
		
		 
	 }
	
	 dashboardMap.put("userNameList", userNameList);
	 dashboardMap.put("user_weixinPayCountArray", user_weixinPayCountArray);
	 dashboardMap.put("user_aliPayCountArray", user_aliPayCountArray);
	 dashboardMap.put("user_weixinPaySumArray", user_weixinPaySumArray);
	 dashboardMap.put("user_aliPaySumArray", user_aliPaySumArray);
	 
	 
	return dashboardMap;
}
/**
 * 根据日期查询
 * @param request
 * @param response
 * @return
 * @throws UnsupportedEncodingException
 */
@RequestMapping( value = "/searchByDate")
public Map<String, Object> searchByDate(HttpServletRequest request,HttpResponse response) throws UnsupportedEncodingException {
	
	//获取http 参数
	Map<String,Object> map = new HashMap<String, Object>();
	request.setCharacterEncoding("UTF-8");
	Map paraMap = HttpRequestParam.showParams(request);
	String dateStr=(String)paraMap.get("date");
	map.put("searchData",dateStr);
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	 long date = 0;
	  try {
		 date = sdf.parse(dateStr).getTime();
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return map;
	}  
	  List<String> userNameList=userInfoService.getUserNameList();
		 int user_weixinPayCountArray[]=new int[userNameList.size()];//个人微信交易成功订单
		 int user_aliPayCountArray[]=new int[userNameList.size()];//个人支付宝交易成功订单
		 double user_weixinPaySumArray[]=new double[userNameList.size()];//个人微信交易成功订单总额
		 double user_aliPaySumArray[]=new double[userNameList.size()];//个人支付宝交易成功订单总额
		 for(int i=0;i<userNameList.size();i++)
		 {
			 String un=userNameList.get(i);
			 try {
				 user_weixinPayCountArray[i]=rsOrderService.getPayCountByUserNameAndBetweenDatetimeAndTradeno("0","1%",un,new Date(date),new Date(date+86400000));
			} catch (Exception e) {
				user_weixinPayCountArray[i]=0;
			}
			 try {
				 user_aliPayCountArray[i]=rsOrderService.getPayCountByUserNameAndBetweenDatetimeAndTradeno("0","2%",un,new Date(date),new Date(date+86400000));
			} catch (Exception e) {
				user_aliPayCountArray[i]=0;
			}
			 try {
				 user_weixinPaySumArray[i]=rsOrderService.getPaySumByUserNameAndBetweenDatetimeAndTradeno("0","1%",un,new Date(date),new Date(date+86400000));
			} catch (Exception e) {
				 user_weixinPaySumArray[i]=0;
			}
			 try {
				 user_aliPaySumArray[i]=rsOrderService.getPaySumByUserNameAndBetweenDatetimeAndTradeno("0","2%",un,new Date(date),new Date(date+86400000));
			} catch (Exception e) {
				user_aliPaySumArray[i]=0;
			}
		 }
		
		 map.put("userNameList", userNameList);
		 map.put("user_weixinPayCountArray", user_weixinPayCountArray);
		 map.put("user_aliPayCountArray", user_aliPayCountArray);
		 map.put("user_weixinPaySumArray", user_weixinPaySumArray);
		 map.put("user_aliPaySumArray", user_aliPaySumArray);
		 map.put("DATA_CODE","SUCCESS");
	return map;		
}
}
