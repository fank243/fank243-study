<div class="layui-side layui-side-menu">
    <div class="layui-side-scroll">
        <div class="layui-logo" lay-href="home/console.html">
            <span>${siteName!'JBoot'}</span>
        </div>
        <ul class="layui-nav layui-nav-tree" lay-shrink="all" id="LAY-system-side-menu" lay-filter="layadmin-system-side-menu">
            <#list menuList as menu>
                <li data-name="${menu.name}" class="layui-nav-item <#if menu_index=0>layui-nav-itemed</#if>">
                    <a href="javascript:;" lay-tips="${menu.name!}" lay-direction="2">
                        <i class="layui-icon ${menu.icon!}"></i>
                        <cite>${menu.name!}</cite>
                    </a>
                    <dl class="layui-nav-child">
                        <#list menu.list as secMenu>
                            <#if secMenu.list?? && (secMenu.list?size > 0)>
                             <dd data-name="${secMenu.name!}" class="layui-nav-itemed">
                                <a href="javascript:;"><i class="layui-icon ${secMenu.icon!}"></i>${secMenu.name!}</a>
                                 <dl class="layui-nav-child">
                                    <#list secMenu.list as thirdMenu>
                                         <dd>
                                             <a lay-href="${thirdMenu.uri!}" <#if (thirdMenu.external)!false>target="_blank"</#if>><i class="layui-icon ${thirdMenu.icon!}"></i>${thirdMenu.name!}</a>
                                         </dd>
                                     </#list>
                                 </dl>
                             </dd>
                             <#else>
                             <dd data-name="${secMenu.name!}">
                                 <a lay-href="${secMenu.uri!}" <#if (secMenu.external)!false>target="_blank"</#if>><i class="layui-icon ${secMenu.icon!}"></i>${secMenu.name!}</a>
                             </dd>
                            </#if>
                        </#list>
                    </dl>
                </li>
            </#list>
        </ul>
    </div>
</div>
