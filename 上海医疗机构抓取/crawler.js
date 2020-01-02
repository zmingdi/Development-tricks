/**
 * Get all the hospital information in shanghai , from website: http://jg.soyi.sh.cn/
 * To run this script, open chrome development tools, nav to script pane, and add "new spinnet". 
 * Paste code inside and run.
 * The hospital information will be in console output. 
 * Post the result to http server using springboot and process later would be better. 
 */

// counter for page, zones
var pageLen = 0;
var curPage = 1;
var curZone = 1;
// total size of shanghai district, manually calculated.
var zoneLen = 16;
// define base URL including marker for zone code and page number
var baseUrl = "http://jg.soyi.sh.cn/h/show?page=[pNo]&type=2&qx=[qx]&traps=&category=&category_s3_ul=&level=&keshi=&keshi2=&service=&tech=&calve=&quick=&stype=&length=10";
var hospitals = [];
var qx = [];
// get all zone code from page
// this code should run in any url inside http://jg.soyi.sh.cn/, so script will detect all the zones in page.
$.each($("#qx>li>a"), function(i, d) {
    var zoneCode = $(d).attr("data");
    if (zoneCode) {
        qx.push(zoneCode);
    }
});

// define formatted output string
var formatOut = "名称 :[name], 地址:[address], 经营性质:[profit],"
     + "发证机关:[fzjg],机构等级:[jglb]" 
     + ",站内地址:http://jg.soyi.sh.cn/h/detail/[inSiteAddress]"
     + ",官网地址:[site]";

console.log(qx);

// loop all the zones, and send ajax request to server. the response is in json format. 
// so parse the json return and console output them.
function loopZones() {
    url = baseUrl.replace('[qx]', qx[curZone]).replace('[pNo]', curPage);
    $.ajax({
        "url": url,
        'type': "GET",
        'dataType': "json",
        'success': function(data) {
            console.log("current zone = " + qx[curZone] + ", current Page = " + curPage);
			// when the page number is 1, it represents the first call in this zone. so get total page number once in ajax request.
            if (curPage == 1) {
                pageLen = data['total_pages'];
            }
            
            
            $.each(data['result'], function(i, d) {
                console.log(formatOut.replace("[name]", d['name'])
                .replace("[address]", d['address'])
                .replace("[profit]", d['jyxz'])
                .replace('[fzjg]', d['fzjg'])
                .replace('[jglb]', d['jglb'])
                .replace('[inSiteAddress]',d['id'])
                .replace('[site]',d['site'] || '')
                );
            });
            // increment the page number
            curPage++;
			// when the current page number is greater than total page length, move zone to next and reset page number.
            if (curPage > pageLen) {
                curZone++;
                curPage = 1;
            }
			// clear interval | terminate script when current zone is end of total.
            if (curZone > zoneLen) {
                clearInterval(x);
            }
        }
    });
}
// call the function regularly. the interval can be shorter. make it 2 secs as safely use. 
var x = setInterval("loopZones()", 2000);
