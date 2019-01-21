<?xml version="1.0"?>
<recipe>
	<instantiate from="src/app_package/Contract.java.ftl"
					to="${escapeXmlAttribute(srcOut)}/${className}Contract.java" />
	<instantiate from="src/app_package/View.java.ftl"
					to="${escapeXmlAttribute(srcOut)}/${className}View.java" />
	<instantiate from="src/app_package/Controller.java.ftl"
					to="${escapeXmlAttribute(srcOut)}/${className}Controller.java" />
	<instantiate from="src/app_package/Model.java.ftl"
					to="${escapeXmlAttribute(srcOut)}/${className}Model.java" />
						
	<instantiate from="res/layout/fragment_blank.xml.ftl"
				   to="${escapeXmlAttribute(resOut)}/layout/${escapeXmlAttribute(layoutName)}.xml" />
				   
	<open file="${escapeXmlAttribute(resOut)}/layout/${escapeXmlAttribute(layoutName)}.xml" />
</recipe>