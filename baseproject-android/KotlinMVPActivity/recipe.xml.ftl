<?xml version="1.0"?>
<recipe>

    <merge from="root/AndroidManifest.xml.ftl"
             to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />

    <instantiate from="root/res/layout/layout_activity.xml.ftl"
            to="${escapeXmlAttribute(resOut)}/layout/${activityView}.xml" />
			
	<instantiate from="root/res/layout/layout_fragment.xml.ftl"
            to="${escapeXmlAttribute(resOut)}/layout/${fragmentView}.xml" />

    <instantiate from="root/src/app_package/MVPActivity.kt.ftl"
             to="${escapeXmlAttribute(srcOut)}/${activityClass}.kt" />
			 
    <instantiate from="root/src/app_package/MVPContract.kt.ftl"
             to="${escapeXmlAttribute(srcOut)}/${contractClass}.kt" />

    <instantiate from="root/src/app_package/MVPData.kt.ftl"
             to="${escapeXmlAttribute(srcOut)}/${dataClass}.kt" />
			 
    <instantiate from="root/src/app_package/MVPFragment.kt.ftl"
             to="${escapeXmlAttribute(srcOut)}/${fragmentClass}.kt" />
			 
    <instantiate from="root/src/app_package/MVPModel.kt.ftl"
             to="${escapeXmlAttribute(srcOut)}/${modelClass}.kt" />
			 
    <instantiate from="root/src/app_package/MVPPresenter.kt.ftl"
             to="${escapeXmlAttribute(srcOut)}/${presenterClass}.kt" />
			 
	<open file="${escapeXmlAttribute(srcOut)}/${activityClass}.kt" />

</recipe>