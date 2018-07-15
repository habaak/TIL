# README

```bash
$ bundle install

$ rails g scaffold posts title:string content:text

$ rake db:migrate
```



##### 서버 실행

`rails s -b 0.0.0.0`



### [네이버 지도 API 튜토리얼](https://navermaps.github.io/maps.js/docs/tutorial-0-Getting-Started.html)

```erb
<div id="map" style="width:100%;height:400px;"></div>

<!--client id는 API신청하면 발급 받을 수 있음-->
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=...&submodules=geocoder"></script>
<script>
  //지도 컨트롤
  var mapOptions = {
      scaleControl: false,
      logoControl: false,
      mapDataControl: false,
      zoomControl: true
  };

  // 네이버 map을 div tag와 연결
  //var map = new naver.maps.Map('map');
  //지도 컨트롤 추가
  var map = new naver.maps.Map('map', mapOptions);

  //지도 제주도로 이동하기(위도-북위, 경도-동경)
  var jeju = new naver.maps.LatLng(33.3590628, 126.534361);
  map.setCenter(jeju); // 중심 좌표 이동
  map.setZoom(13);     // 줌 레벨 변경

</script>
```



#### [지도 컨트롤 기능](https://navermaps.github.io/maps.js/docs/tutorial-Controls.html)

- 줌 컨트롤: 지도의 줌 레벨을 조정할 수 있는 슬라이더 또는 +/- 버튼입니다. 이 컨트롤은 기본적으로 사용하지 않도록 되어 있습니다.
- 축척 컨트롤: 지도의 축척을 표시합니다. 이 컨트롤은 기본적으로 오른쪽 아래에 표시됩니다.
- 지도 유형 컨트롤: 일반 지도, 위성 지도와 같은 지도의 지도 유형을 전환할 수 있는 버튼 또는 드롭다운입니다. 이 컨트롤은 기본적으로 사용하지 않도록 되어 있습니다.
- 지도 데이터 저작권 컨트롤: 지도 데이터의 저작권을 표시합니다. 이 컨트롤은 기본적으로 왼쪽 아래에 표시됩니다.
- NAVER 로고 컨트롤: NAVER 로고를 표시합니다. 이 컨트롤은 기본적으로 오른쪽 아래에 표시됩니다.



#### [마커](https://navermaps.github.io/maps.js/docs/tutorial-Marker.html)

- 다수의 마커에 이벤트 핸들러 사용하기
- 보이는 지도 영역의 마커만 표시하기



DB 입력

```bash
$ bundle install
$ rails g model School name:string address:string lng:float lat:float
```

```ruby
# db/seeds.rb
# model에 접근할 때는 첫 글자는 대문자로 해야 한다.
require 'csv'
CSV.foreach(Rails.root.join('school.csv'), headers: true) do |row|
  School.create! row.to_hash
end
```

> $ rake db:seed
>
> 만일 잘 작동되는지 보고 싶으면 새로운 터미널에서 log를 찍어서 확인해보기
>
> $ tail -F log/development.log
