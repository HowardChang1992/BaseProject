<?xml version="1.0"?>
<recipe>

    <merge from="root/AndroidManifest.xml.ftl"
             to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />

    <instantiate from="root/res/layout/layout_activity.xml.ftl"
            to="${escapeXmlAttribute(resOut)}/layout/${activityView}.xml" />
			
	<instantiate from="root/res/layout/layout_fragment.xml.ftl"
            to="${escapeXmlAttribute(resOut)}/layout/${fragmentView}.xml" />



    <instantiate from="root/src/app_package/MVVMActivity.kt.ftl"
             to="${escapeXmlAttribute(srcOut)}/${activityClass}.kt" />
			 
    <instantiate from="root/src/app_package/MVVMData.kt.ftl"
             to="${escapeXmlAttribute(srcOut)}/${dataClass}.kt" />

    <instantiate from="root/src/app_package/MVVMFragment.kt.ftl"
             to="${escapeXmlAttribute(srcOut)}/${fragmentClass}.kt" />
			 
    <instantiate from="root/src/app_package/MVVMViewModel.kt.ftl"
             to="${escapeXmlAttribute(srcOut)}/${viewModelClass}.kt" />
			 
	<open file="${escapeXmlAttribute(srcOut)}/${activityClass}.kt" />

</recipe>