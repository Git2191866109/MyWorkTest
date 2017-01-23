${user.name}
<#--在framker中不要以is开头-->
${user.noIsTest?c}
<#--在framker中如果以is开头：1. 改boolean 为Boolean 2. 将生成的get set方法中的is加上-->
${user.isTest?c}