<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/recipes">
<html>
<body>
<table border="1">
    <tr>
      <th>Name</th>
      <th>Source</th>
    </tr>
    <xsl:for-each select="recipe">
    <tr>
      <td><xsl:value-of select="name" /></td>
      <td><xsl:value-of select="source" /></td>
    </tr>
    </xsl:for-each>
</table>
</body>
</html>
</xsl:template>
</xsl:stylesheet>