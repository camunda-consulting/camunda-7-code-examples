<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:omgdc="http://www.omg.org/spec/DD/20100524/DC">
	<xsl:template match="//omgdc:Bounds[@height=55 and @width=105]">
		<!-- resize Activities to default size of camunda Modeler -->
		<omgdc:Bounds height="80.0" width="100.0" x="{@x + 2.5}" y="{@y - 12.5}" />
	</xsl:template>
	<!-- standard copy template -->
	<xsl:template match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*"/>
			<xsl:apply-templates/>
		</xsl:copy>
	</xsl:template>	
</xsl:stylesheet>