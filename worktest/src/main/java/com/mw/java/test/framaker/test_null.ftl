<#--${nullValue}-->
<#--! 号来解决变量值为null或者不存在的情况-->
${nullValue!"nullValue值是null"}
${nullValue!"100+1"}
${nullValue!(100+1)}

<#if abc??>
    exist abc
<#else>
    not exist abc
</#if>

<#if nullValue??>
exist nullValue
<#else>
not exist nullValue
</#if>

<#if nullValuestring??>
exist nullValuestring ${nullValuestring}
<#else>
not exist nullValuestring
</#if>