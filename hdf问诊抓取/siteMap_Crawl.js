var baseUrl = 'https://www.haodf.com/sitemap-zx/2019/';
var datesUrl = $.map($($("div.dis_article_2").get(1)).find("li>a"), function(d){
    return $(d).attr("href");
});
urls = [];
var dateCounter = 21;
var max_date = 2;
function sleep(n) {
    var start = new Date().getTime();
    while(true) if(new Date().getTime()-start > n*1000) break;
}

function urlMode(url, title) {
	return { "address":url, "title":title };
}

function postResults(result) {
	$.ajax({
					"url":"http://localhost:8830/url/list",
					"traditional":true,
					"type":"POST",
					"data" :result,
					"dataType":"json",
					"contentType": "application/json",
					"success":function(data){
						
					},
					"error": function() {
						console.log("post data failed, retry within 5 secs");
						setTimeout("postResults('"+result+"')",4168);
					}
				});
}
function processSingleDate(sdUrl) {
    console.log("processing page = " + sdUrl);
    $.ajax({
        url:sdUrl,
        type:"GET",
        dataType:"html",
        complete:function(response,status) {
        	if(status == 'error') {
        		console.log("retry page " + sdUrl + " in 5 secs.....");
        		setTimeout("processSingleDate('"+sdUrl+"')",6815);
        	} else {
				var localData = [];
        		$.each($(response.responseText).find(".hh>a"), function(i,l) {
					urls.push(l.href);
					//localData.push(urlMode(l.href,$(l).text()));
					localData.push({ "address":l.href, "title":$(l).text() });
				});
				postResults(JSON.stringify(localData));
				var next = $(response.responseText).find('a.p_num:contains("下一页")');
				if(next.attr("href")){
					//console.log("next page = " + next.attr("href"));
					setTimeout("processSingleDate('"+next.attr("href")+"')",2618);
				}
        	}
			
			
        },

    });
}

var len = datesUrl.length; //dateCounter+7;//
while(dateCounter<len) {
	processSingleDate(datesUrl[dateCounter++]);
	
	sleep(1);
	if(dateCounter%5==0) {
		console.log("processing " + dateCounter + " page, page date is " + datesUrl[dateCounter]);
		
	}
}	





