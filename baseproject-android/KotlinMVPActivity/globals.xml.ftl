<?xml version="1.0"?>
<globals>
    <#include "../common/common_globals.xml.ftl" />
    <global id="fragmentClass" value="${pageClass}Fragment" />
	<global id="activityClass" value="${pageClass}Activity" />
	<global id="contractClass" value="${pageClass}Contract" />
	<global id="modelClass" value="${pageClass}Model" />
	<global id="presenterClass" value="${pageClass}Presenter" />
	<global id="componentClass" value="${pageClass}Component" />
	<global id="dataClass" value="${pageClass}Data" />
	<global id="dataParameter" value="${lowercasePageName}Data" />
	
	<global id="activityView" value="${activityToLayout(pageClass)}" />
	<global id="fragmentView" value="fragment_${classToResource(pageClass)}" />

</globals>
