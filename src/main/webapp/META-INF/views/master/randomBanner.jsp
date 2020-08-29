<%@page import="acme.components.BannerAdvisor"%>
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<%  %>
<div class="rounded">
	<jstl:out value=""></jstl:out>
	
	<p style="text-align:center;">
		<a href="${banner.target}" target="_blank">
			<img style="max-height:150px;" src="${banner.picture}" alt="${banner.slogan}" class="img-fluid rounded"/>
		</a>
	</p>
</div>