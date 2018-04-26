### Android HTTP Multipart Network

파일을 전송하기 위해서 필요한 통신 방법이다.
안드로이드에서는 AsyncTask를 사용하여 통신 task를 수행했다.
전달할 data들을 Json Object에 담아 전송했다.


1) Gradle 추가하기
```
    compile group: 'org.apache.httpcomponents', name: 'httpcore', version: '4.2.2'
    compile group: 'org.apache.james', name: 'apache-mime4j', version: '0.6.1'
    compile group: 'org.apache.httpcomponents', name: 'httpmime', version: '4.3.1'
```
2) 통신 부분 java Class
```Java
public class PostPicRequest extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            postPic(strings[0],strings[1]);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

        }

        JSONObject jsonDataObject = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        public void postPic(String url,String comment){
            try
            {
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);

                FileBody bin = new FileBody(pic);
                MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                builder.addPart("img", bin);
                builder.addTextBody("cmt",comment,ContentType.create("Multipart/related", "UTF-8"));
                InputStream inputStream = null;
                post.setEntity(builder.build());

                HttpResponse response = client.execute(post);
                HttpEntity entity = response.getEntity();

                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                String line = "";
                while ((line = rd.readLine()) != null) {
                    System.out.println("Buffered rd -- "+line);
                    JSONArray jsonArray = new JSONArray(line);
                    System.out.println("JSONArray -- "+jsonArray);

                    for(int i = 0 ; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String isSuccess = jsonObject.getString("registerContentsSucess");
                        System.out.println(isSuccess+ " - POST - "+isSuccess);
                        registerContentsSucess=isSuccess;
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
```
