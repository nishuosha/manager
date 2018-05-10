<div class="pagination pagination-right">
    <span style="float: left">显示第 ${(pageNow-1)*pageSize+1} 至 ${(pageNow-1)*pageSize+pageSize} 项结果，共 ${total} 项</span>
    <ul>
        <li class="previous <#if (pageNow <= 1)>disabled</#if>"><a href="#" onclick="pageTurn(${pageNow-1});return false;">上页</a></li>
    <#if (pageCount <= 5)>
        <#list 1..pageCount as i>
            <li class="#"><a href="#" <#if (pageNow == i)>class="btn"</#if> onclick="pageTurn(${i});return false;" target="_self">${i}</a></li>
        </#list>
    <#elseif (pageNow < 3)>
        <#list 1..3 as i>
            <li class="#"><a href="#" <#if (pageNow == i)>class="btn"</#if> onclick="pageTurn(${i});return false;" target="_self">${i}</a></li>
        </#list>
        <li class="#"><a href="#" target="_self">...</a></li>
        <li class="#"><a href="#" <#if (pageNow == pageCount)>class="btn"</#if> onclick="pageTurn(${pageCount});return false;" target="_self">${pageCount}</a></li>
    <#elseif (pageNow > pageCount - 2)>
        <li class="#"><a href="#" <#if (pageNow == 1)>class="btn"</#if> onclick="pageTurn(1);return false;" target="_self">1</a></li>
        <li class="#"><a href="#" target="_self">...</a></li>
        <#list (pageCount - 2)..pageCount as i>
            <li class="#"><a href="#" <#if (pageNow == i)>class="btn"</#if> onclick="pageTurn(${i});return false;" target="_self">${i}</a></li>
        </#list>
    <#else>
        <li class="#"><a href="#" <#if (pageNow == 1)>class="btn"</#if> onclick="pageTurn(1);return false;" target="_self">1</a></li>
        <#if (pageNow != 3)><li class="#"><a href="#" target="_self">...</a></li></#if>
        <#list (pageNow - 1)..(pageNow + 1) as i>
            <li class="#"><a href="#" <#if (pageNow == i)>class="btn"</#if> onclick="pageTurn(${i});return false;" target="_self">${i}</a></li>
        </#list>
        <#if (pageNow != (pageCount-2))><li class="#"><a href="#" target="_self">...</a></li></#if>
        <li class="#"><a href="#" <#if (pageNow == pageCount)>class="btn"</#if> onclick="pageTurn(${pageCount});return false;" target="_self">${pageCount}</a></li>
    </#if>
        <li class="next <#if (pageNow >= pageCount)>disabled</#if>"><a href="#" onclick="pageTurn(${pageNow+1})">下页</a></li>
    </ul>
</div>