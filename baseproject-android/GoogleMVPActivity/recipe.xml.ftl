<?xml version="1.0"?>
<recipe>

    <merge from="root/AndroidManifest.xml.ftl"
             to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />

    <instantiate from="root/res/layout/layout_activity.xml.ftl"
            to="${escapeXmlAttribute(resOut)}/layout/${activityView}.xml" />
			
	<instantiate from="root/res/layout/layout_fragment.xml.ftl"
            to="${escapeXmlAttribute(resOut)}/layout/${fragmentView}.xml" />

    <instantiate from="root/src/app_package/MVPActivity.java.ftl"
             to="${escapeXmlAttribute(srcOut)}/${activityClass}.java" />
			 
    <instantiate from="root/src/app_package/MVPComponent.java.ftl"
             to="${escapeXmlAttribute(srcOut)}/${componentClass}.java" />
			 
    <instantiate from="root/src/app_package/MVPContract.java.ftl"
             to="${escapeXmlAttribute(srcOut)}/${contractClass}.java" />
			 
    <instantiate from="root/src/app_package/MVPFragment.java.ftl"
             to="${escapeXmlAttribute(srcOut)}/${fragmentClass}.java" />
			 
    <instantiate from="root/src/app_package/MVPModel.java.ftl"
             to="${escapeXmlAttribute(srcOut)}/${modelClass}.java" />
			 
    <instantiate from="root/src/app_package/MVPPresenter.java.ftl"
             to="${escapeXmlAttribute(srcOut)}/${presenterClass}.java" />
			 
    <instantiate from="root/src/app_package/MVPPresenterModule.java.ftl"
             to="${escapeXmlAttribute(srcOut)}/${presenterModuleClass}.java" />
			 
	<open file="${escapeXmlAttribute(srcOut)}/${activityClass}.java" />

</recipe>