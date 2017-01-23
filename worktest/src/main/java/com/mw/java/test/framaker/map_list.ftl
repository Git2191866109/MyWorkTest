<#list map_list?keys as key>
    <#if key == 'list_str'>
    ${key}：
        <#list map_list[key] as u>
        ${u}
        </#list>
    </#if>
    <#if key == 'list_obj'>
    ${key}：
        <#list map_list[key] as u>
        ${u.id} 和 ${u.city}
        </#list>
    </#if>
    <#if key == 'list_oebj'>
    key11
    </#if>
</#list>