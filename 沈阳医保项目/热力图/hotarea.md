#沈阳医保项目
## 热力图

沈阳医保家庭医生项目当中， 居民与医院建立签约服务关系。 根据居民预留的地址， 集成百度地图的heatmap组件， 生成签约居民分布热力图。 

其目的在于在常规的统计当中， 只能以柱状图， 饼图， 条形图进行数据展示。 以地图的形式进行显示， 可以让统计人员， 领导， 更加直观的发现签约工作的进行情况。 根据地图上的居民热力情况和医院的地址信息， 更好的了解家庭医生的推广行为
![](./hotarea.jpg)

## 实现
1. 从数据库中获得居民居住地信息。
2. 通过百度API， 根据地址信息， 抽取GPS信息。并保存在数据库中lng, lat字段当中。

		  allRows = cursor.fetchall();
	      preurl='http://api.map.baidu.com/geocoder/v2/?address='
	      posturl='&output=json&city=%E6%B2%88%E9%98%B3%E5%B8%82&ak=1mFuOI0E7mSw4ot5188kcvG2LpIfdQnu'
	      data=[]
	      for r in allRows:
	          d={}
	          d['count']=r[0];
	          d['lng']=r[1]
	          d['lat']=r[2]
	          data.append(d)
	          req = ureq.Request(url = '%s%s%s' % (preurl,parse.quote(r[1]),posturl))
	          res = ureq.urlopen(req)
	          res = res.read()
	          #print(res.decode(encoding='utf-8')[0])
	          d=json.loads(res.decode(encoding='utf-8'))
	          if d['status']==0:
	              print(r[1])
	              # print(d['result']['location'])
	              isql='update resident_GPS set lng=%s, lat=%s where id=%d' %(d['result']['location']['lng'],d['result']['location']['lat'],r[0])
	              cursor.execute(isql)


3. 展示的时候， 由于居民信息比较多， 目前采用将GPS信息取前端5位进行group，以免打点显示的时候过于密集。
	
		SELECT count(*), left(lng, 7),left(lat,7) FROM fdms.resident_gps where lng!='' group by left(lng,5),left(lat,5)

4. 利用百度提供的热力图组件， 进行地图绘制， [代码如下](./hotarea.html)。