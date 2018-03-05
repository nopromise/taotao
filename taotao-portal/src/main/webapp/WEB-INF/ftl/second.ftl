<html>
<head>
	<title>${title!}</title>
	<#include "first.ftl">
</head>
<body>
	<label>学号：</label>${student.id}<br>
	<label>姓名：</label>${student.name}<br>
	<label>住址：</label>${student.address}<br>
	
	<table border="1">
	<#list students as s>
	<tr>
	<td>${s.id}</td>
	<td>${s.name}</td>
	<td>${s.address}</td>
	</tr>
	</#list>
	</table>
	
	<#list names as n>
	${n}
	</#list>
	<br>
	<#list balls as b>
	<#if b_index%2==0>
	${b}</br>
	<#else>
	${b_index}
	${b}</br>
	</#if>
	

	</#list>
	
	<br>
	<#if curdate??>
	${curdate?string("yyyy-MM-dd HH:mm:ss")}
	<#else>
	curdate为空
	</#if>
	
	
	
</body>
</html>
