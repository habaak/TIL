package r;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class RTest3 {

	public static void main(String[] args) {
		RConnection rconn = null;
		try {
			rconn = new RConnection();

		} catch (RserveException e) {
			System.out.println("R Connection Error");

		}
		System.out.println("ok");
		try {
			rconn.setStringEncoding("utf8");

			rconn.eval("source('C:/rproject/day08/r1.R',encoding='UTF-8')");
			RList list = rconn.eval("r3()").asList();
			System.out.println(list.size());
			String time[] = list.at("time").asStrings();
			double line2[] = list.at("line2").asDoubles();
			double line3[] = list.at("line3").asDoubles();
			double line4[] = list.at("line4").asDoubles();
			
			/*System.out.println(list.names);
			for (int i = 0; i < time.length; i++) {
				System.out.printf("%d %5s %5.0f %5.0f %5.0f\n",i+1,time[i],line2[i],line3[i],line4[i]);
			}*/
			
			JSONArray ja = new JSONArray();
			JSONObject jo1 = new JSONObject();
			JSONObject jo2 = new JSONObject();
			JSONObject jo3 = new JSONObject();
			JSONObject jo4 = new JSONObject();
			JSONArray jtime = new JSONArray();
			JSONArray jline2 = new JSONArray();
			JSONArray jline3 = new JSONArray();
			JSONArray jline4 = new JSONArray();
			for (int i = 0; i < line4.length; i++) {
				jtime.add(time[i]);
				jline2.add(line2[i]);
				jline3.add(line3[i]);
				jline4.add(line4[i]);
			}
			
			jo1.put("time", jtime);
			jo2.put("line2", jline2);
			jo3.put("line3", jline3);
			jo4.put("line4", jline4);
			ja.add(jo1);
			ja.add(jo2);
			ja.add(jo3);
			ja.add(jo4);
			System.out.println(ja.toJSONString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}