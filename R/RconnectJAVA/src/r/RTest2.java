package r;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class RTest2 {

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
			double datas[] = rconn.eval("r2()").asDoubles();

			JSONArray ja = new JSONArray();
			JSONObject jo = new JSONObject();
			JSONArray jdatas = new JSONArray();
			for (double d : datas) {
				jdatas.add(d);
			}
			jo.put("names", "data");
			jo.put("datas", jdatas);
			ja.add(jo);

			System.out.println(ja.toJSONString());
			/*
			 * for(double d:datas) { System.out.println(d); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
