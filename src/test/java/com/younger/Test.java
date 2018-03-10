package com.younger;

import org.json.JSONArray;
import org.json.JSONObject;

import com.younger.entity.Dapanzs;

public class Test {
	
	private Dapanzs getDapanzs(){
		String C1Cache="{quotation:[\"0000011,上证指数,3253.24,198428585984,3.46,0.11%,493|158|726|94,591|258|1217|210\","+
				"\"3990012,深证成指,10437.94,260555624448,42.74,0.41%,493|158|726|94,591|258|1217|210\"]}";
		  JSONObject jsonObj = new JSONObject(C1Cache);
//		  Object obj =  jsonObj.get("quotation"); //arraylist
		  JSONArray jsonArray = jsonObj.getJSONArray("quotation");
		  Dapanzs dapan = new Dapanzs();
		  if(jsonArray==null || jsonArray.length()!=2 ) return null;
		  
			  for(int i=0;i<jsonArray.length() ;i++){
				  String str = jsonArray.getString(i);
				  System.out.println(str);
				  String[] sps = str.split(",");
				  if(sps.length!=8){
					  throw new RuntimeException("invalid json str: "+ str);
				  }
//				  0000011,上证指数,3253.24,198428585984,3.46,0.11%,493|158|726|94,591|258|1217|210
				  double zhishu = Double.valueOf( sps[2] );
				  double deal = Double.valueOf(sps[3]);
				  double zhishudelta =  Double.valueOf( sps[4] );
				  String zhishudeltaratio =   sps[5];
				  int num_up = Integer.valueOf(sps[6].split("\\|")[0]);
				  int num_ping = Integer.valueOf(sps[6].split("\\|")[1]);
				  int num_down = Integer.valueOf(sps[6].split("\\|")[2]);
				  int num_ting = Integer.valueOf(sps[6].split("\\|")[3]);
				 if(i==0){
					  dapan.setP_huzhi(zhishu);
					  dapan.setP_huDeal(deal);
					  dapan.setP_huDelta(zhishudelta);
					  dapan.setP_huDeltaRatio(zhishudeltaratio);
					  dapan.setP_hunum_up(num_up);
					  dapan.setP_hunum_ping(num_ping);
					  dapan.setP_hunum_down(num_down);
				 }else{
					  dapan.setP_szzhi(zhishu);
					  dapan.setP_szDeal(deal);
					  dapan.setP_szDelta(zhishudelta);
					  dapan.setP_szDeltaRatio(zhishudeltaratio);
					  dapan.setP_sznum_up(num_up);
					  dapan.setP_sznum_ping(num_ping);
					  dapan.setP_sznum_down(num_down);
				 }
//				  System.out.println("class: "+jsonArray.get(i).getClass() + " value: "+ jsonArray.get(i).toString() );
			  }
			  System.out.println(dapan.toGsonStr());
			  return dapan;
		  }
	

	public static void main(String[] args) {
		
	}

}
