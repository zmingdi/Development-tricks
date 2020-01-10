
function sleep(n) {
    var start = new Date().getTime();
    console.log("start time = " +new Date());
    while(true) if(new Date().getTime()-start > n*1000) break;
    console.log("end time = " +new Date());
}
function consult(data) {

    var texts = data.find("div.f-c-r-wrap");
    var contents = [];
    $.each(texts, function(i,d){
        contents.push($(d).text());
    });
    console.log(contents);
}
var pageUrl = "https://zixun.haodf.com/dispatched/menzhen.htm?p=[pNo]";
var pageLen = 2;
var curPage = 1;
var details=[];
while(curPage<pageLen) {
    $.ajax({
       'url':pageUrl.replace('[pNo]',curPage),
       'type':'GET',
       'dataType':'html',
       'success':function(data){
           $.each($(data).find("span.fl>a.fl"), function(i,d){
               details.push($(d).attr("href")); 
            });
            console.log("detail cache size = " + details.length);
       },
    });
    
    console.log("page number = " + curPage);
    curPage++;
    sleep(3);
}
sleep(3);
//console.log("details = " + details);

