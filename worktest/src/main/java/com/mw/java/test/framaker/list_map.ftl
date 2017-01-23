<#list list_map as eachMap>
<#list eachMap?keys as key>
${key} 和 ${eachMap[key]}
</#list>
</#list>