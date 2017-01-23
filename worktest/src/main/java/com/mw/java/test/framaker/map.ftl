<#list map_str?keys as key>
${key} 和 ${map_str[key]}
</#list>

<#list map_obj?keys as key>
${key} 和 ${map_obj[key].id} 和 ${key} 和 ${map_obj[key].city}
</#list>