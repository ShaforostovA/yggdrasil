"use strict";(self["webpackChunkyggdrasil_client"]=self["webpackChunkyggdrasil_client"]||[]).push([[543],{4501:function(e,t,s){s.d(t,{Z:function(){return w}});var a=s(3396),r=s(7139),i=s(9242),l=(e,t)=>{const s=e.__vccOpts||e;for(const[a,r]of t)s[a]=r;return s};const n={data(){return{innerValue:1}},props:{modelValue:{type:Number},pageCount:{type:Number,required:!0},initialPage:{type:Number,default:1},forcePage:{type:Number},clickHandler:{type:Function,default:()=>{}},pageRange:{type:Number,default:3},marginPages:{type:Number,default:1},prevText:{type:String,default:"Prev"},nextText:{type:String,default:"Next"},breakViewText:{type:String,default:"…"},containerClass:{type:String,default:"pagination"},pageClass:{type:String,default:"page-item"},pageLinkClass:{type:String,default:"page-link"},prevClass:{type:String,default:"page-item"},prevLinkClass:{type:String,default:"page-link"},nextClass:{type:String,default:"page-item"},nextLinkClass:{type:String,default:"page-link"},breakViewClass:{type:String},breakViewLinkClass:{type:String},activeClass:{type:String,default:"active"},disabledClass:{type:String,default:"disabled"},noLiSurround:{type:Boolean,default:!1},firstLastButton:{type:Boolean,default:!1},firstButtonText:{type:String,default:"First"},lastButtonText:{type:String,default:"Last"},hidePrevNext:{type:Boolean,default:!1}},computed:{selected:{get:function(){return this.modelValue||this.innerValue},set:function(e){this.innerValue=e}},pages:function(){let e={};if(this.pageCount<=this.pageRange)for(let t=0;t<this.pageCount;t++){let s={index:t,content:t+1,selected:t===this.selected-1};e[t]=s}else{const t=Math.floor(this.pageRange/2);let s=t=>{let s={index:t,content:t+1,selected:t===this.selected-1};e[t]=s},a=t=>{let s={disabled:!0,breakView:!0};e[t]=s};for(let e=0;e<this.marginPages;e++)s(e);let r=0;this.selected-t>0&&(r=this.selected-1-t);let i=r+this.pageRange-1;i>=this.pageCount&&(i=this.pageCount-1,r=i-this.pageRange+1);for(let e=r;e<=i&&e<=this.pageCount-1;e++)s(e);r>this.marginPages&&a(r-1),i+1<this.pageCount-this.marginPages&&a(i+1);for(let e=this.pageCount-1;e>=this.pageCount-this.marginPages;e--)s(e)}return e}},methods:{handlePageSelected(e){this.selected!==e&&(this.innerValue=e,this.$emit("update:modelValue",e),this.clickHandler(e))},prevPage(){this.selected<=1||this.handlePageSelected(this.selected-1)},nextPage(){this.selected>=this.pageCount||this.handlePageSelected(this.selected+1)},firstPageSelected(){return 1===this.selected},lastPageSelected(){return this.selected===this.pageCount||0===this.pageCount},selectFirstPage(){this.selected<=1||this.handlePageSelected(1)},selectLastPage(){this.selected>=this.pageCount||this.handlePageSelected(this.pageCount)}},beforeMount(){this.innerValue=this.initialPage},beforeUpdate(){void 0!==this.forcePage&&this.forcePage!==this.selected&&(this.selected=this.forcePage)}},o=["tabindex","innerHTML"],d=["tabindex","innerHTML"],u=["onClick","onKeyup"],c=["tabindex","innerHTML"],p=["tabindex","innerHTML"],h=["innerHTML"],g=["innerHTML"],m=["onClick","onKeyup"],y=["innerHTML"],f=["innerHTML"];function x(e,t,s,l,n,x){return s.noLiSurround?((0,a.wg)(),(0,a.iD)("div",{key:1,class:(0,r.C_)(s.containerClass)},[s.firstLastButton?((0,a.wg)(),(0,a.iD)("a",{key:0,onClick:t[8]||(t[8]=e=>x.selectFirstPage()),onKeyup:t[9]||(t[9]=(0,i.D2)((e=>x.selectFirstPage()),["enter"])),class:(0,r.C_)([s.pageLinkClass,x.firstPageSelected()?s.disabledClass:""]),tabindex:"0",innerHTML:s.firstButtonText},null,42,h)):(0,a.kq)("",!0),x.firstPageSelected()&&s.hidePrevNext?(0,a.kq)("",!0):((0,a.wg)(),(0,a.iD)("a",{key:1,onClick:t[10]||(t[10]=e=>x.prevPage()),onKeyup:t[11]||(t[11]=(0,i.D2)((e=>x.prevPage()),["enter"])),class:(0,r.C_)([s.prevLinkClass,x.firstPageSelected()?s.disabledClass:""]),tabindex:"0",innerHTML:s.prevText},null,42,g)),((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(x.pages,(t=>((0,a.wg)(),(0,a.iD)(a.HY,null,[t.breakView?((0,a.wg)(),(0,a.iD)("a",{key:t.index,class:(0,r.C_)([s.pageLinkClass,s.breakViewLinkClass,t.disabled?s.disabledClass:""]),tabindex:"0"},[(0,a.WI)(e.$slots,"breakViewContent",{},(()=>[(0,a.Uk)((0,r.zw)(s.breakViewText),1)]))],2)):t.disabled?((0,a.wg)(),(0,a.iD)("a",{key:t.index,class:(0,r.C_)([s.pageLinkClass,t.selected?s.activeClass:"",s.disabledClass]),tabindex:"0"},(0,r.zw)(t.content),3)):((0,a.wg)(),(0,a.iD)("a",{key:t.index,onClick:e=>x.handlePageSelected(t.index+1),onKeyup:(0,i.D2)((e=>x.handlePageSelected(t.index+1)),["enter"]),class:(0,r.C_)([s.pageLinkClass,t.selected?s.activeClass:""]),tabindex:"0"},(0,r.zw)(t.content),43,m))],64)))),256)),x.lastPageSelected()&&s.hidePrevNext?(0,a.kq)("",!0):((0,a.wg)(),(0,a.iD)("a",{key:2,onClick:t[12]||(t[12]=e=>x.nextPage()),onKeyup:t[13]||(t[13]=(0,i.D2)((e=>x.nextPage()),["enter"])),class:(0,r.C_)([s.nextLinkClass,x.lastPageSelected()?s.disabledClass:""]),tabindex:"0",innerHTML:s.nextText},null,42,y)),s.firstLastButton?((0,a.wg)(),(0,a.iD)("a",{key:3,onClick:t[14]||(t[14]=e=>x.selectLastPage()),onKeyup:t[15]||(t[15]=(0,i.D2)((e=>x.selectLastPage()),["enter"])),class:(0,r.C_)([s.pageLinkClass,x.lastPageSelected()?s.disabledClass:""]),tabindex:"0",innerHTML:s.lastButtonText},null,42,f)):(0,a.kq)("",!0)],2)):((0,a.wg)(),(0,a.iD)("ul",{key:0,class:(0,r.C_)(s.containerClass)},[s.firstLastButton?((0,a.wg)(),(0,a.iD)("li",{key:0,class:(0,r.C_)([s.pageClass,x.firstPageSelected()?s.disabledClass:""])},[(0,a._)("a",{onClick:t[0]||(t[0]=e=>x.selectFirstPage()),onKeyup:t[1]||(t[1]=(0,i.D2)((e=>x.selectFirstPage()),["enter"])),class:(0,r.C_)(s.pageLinkClass),tabindex:x.firstPageSelected()?-1:0,innerHTML:s.firstButtonText},null,42,o)],2)):(0,a.kq)("",!0),x.firstPageSelected()&&s.hidePrevNext?(0,a.kq)("",!0):((0,a.wg)(),(0,a.iD)("li",{key:1,class:(0,r.C_)([s.prevClass,x.firstPageSelected()?s.disabledClass:""])},[(0,a._)("a",{onClick:t[2]||(t[2]=e=>x.prevPage()),onKeyup:t[3]||(t[3]=(0,i.D2)((e=>x.prevPage()),["enter"])),class:(0,r.C_)(s.prevLinkClass),tabindex:x.firstPageSelected()?-1:0,innerHTML:s.prevText},null,42,d)],2)),((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(x.pages,(t=>((0,a.wg)(),(0,a.iD)("li",{key:t.index,class:(0,r.C_)([s.pageClass,t.selected?s.activeClass:"",t.disabled?s.disabledClass:"",t.breakView?s.breakViewClass:""])},[t.breakView?((0,a.wg)(),(0,a.iD)("a",{key:0,class:(0,r.C_)([s.pageLinkClass,s.breakViewLinkClass]),tabindex:"0"},[(0,a.WI)(e.$slots,"breakViewContent",{},(()=>[(0,a.Uk)((0,r.zw)(s.breakViewText),1)]))],2)):t.disabled?((0,a.wg)(),(0,a.iD)("a",{key:1,class:(0,r.C_)(s.pageLinkClass),tabindex:"0"},(0,r.zw)(t.content),3)):((0,a.wg)(),(0,a.iD)("a",{key:2,onClick:e=>x.handlePageSelected(t.index+1),onKeyup:(0,i.D2)((e=>x.handlePageSelected(t.index+1)),["enter"]),class:(0,r.C_)(s.pageLinkClass),tabindex:"0"},(0,r.zw)(t.content),43,u))],2)))),128)),x.lastPageSelected()&&s.hidePrevNext?(0,a.kq)("",!0):((0,a.wg)(),(0,a.iD)("li",{key:2,class:(0,r.C_)([s.nextClass,x.lastPageSelected()?s.disabledClass:""])},[(0,a._)("a",{onClick:t[4]||(t[4]=e=>x.nextPage()),onKeyup:t[5]||(t[5]=(0,i.D2)((e=>x.nextPage()),["enter"])),class:(0,r.C_)(s.nextLinkClass),tabindex:x.lastPageSelected()?-1:0,innerHTML:s.nextText},null,42,c)],2)),s.firstLastButton?((0,a.wg)(),(0,a.iD)("li",{key:3,class:(0,r.C_)([s.pageClass,x.lastPageSelected()?s.disabledClass:""])},[(0,a._)("a",{onClick:t[6]||(t[6]=e=>x.selectLastPage()),onKeyup:t[7]||(t[7]=(0,i.D2)((e=>x.selectLastPage()),["enter"])),class:(0,r.C_)(s.pageLinkClass),tabindex:x.lastPageSelected()?-1:0,innerHTML:s.lastButtonText},null,42,p)],2)):(0,a.kq)("",!0)],2))}var w=l(n,[["render",x]])},1352:function(e,t,s){var a=s(4161),r=s(2479);const i="http://10.100.124.9/api/v1/reports/";class l{getReport(e){return a.Z.get(i+e,{headers:(0,r.Z)()})}getAllReports(e,t,s,l,n,o,d,u){let c=JSON.stringify({parameter:e,currentPage:t,sortField:s,sortDir:l,statusName:n,minDate:o,maxDate:d,reportType:u}),p={method:"post",maxBodyLength:1/0,url:i+"all",headers:(0,r.Z)(),data:c};return a.Z.request(p)}getAllReportStructures(e,t,s,l,n,o,d,u){let c=JSON.stringify({parameter:e,currentPage:t,sortField:s,sortDir:l,isActive:n,minDate:o,maxDate:d,reportTypeName:u}),p={method:"post",maxBodyLength:1/0,url:i+"structures/all",headers:(0,r.Z)(),data:c};return a.Z.request(p)}getAllActiveReportStructures(){return a.Z.get(i+"structures/active/all",{headers:(0,r.Z)()})}getReportStructureById(e){return a.Z.get(i+"structures/"+e,{headers:(0,r.Z)()})}getAllReportTypes(){return a.Z.get(i+"types/all",{headers:(0,r.Z)()})}checkReportTypeName(e){let t=JSON.stringify({name:e}),s={method:"post",maxBodyLength:1/0,url:i+"types/check",headers:(0,r.Z)(),data:t};return a.Z.request(s)}createReportType(e){let t=JSON.stringify({name:e}),s={method:"post",maxBodyLength:1/0,url:i+"types/create",headers:(0,r.Z)(),data:t};return a.Z.request(s)}checkReportStructure(e){return a.Z.get(i+`structures/${e}/check`,{headers:(0,r.Z)()})}createReportStructure(e,t,s,l,n){let o=JSON.stringify({id:e,name:t,description:s,reportTypeId:l,structureData:JSON.stringify(n)}),d={method:"post",maxBodyLength:1/0,url:i+"structures/create",headers:(0,r.Z)(),data:o};return a.Z.request(d)}updateStatusReportStructure(e,t){let s=JSON.stringify({id:e,isActive:Boolean(t)}),l={method:"post",maxBodyLength:1/0,url:i+"structures/status/update",headers:(0,r.Z)(),data:s};return a.Z.request(l)}updateReportStructure(e,t,s,l,n){let o=JSON.stringify({id:e,name:t,description:s,reportTypeId:l,structureData:JSON.stringify(n)}),d={method:"post",maxBodyLength:1/0,url:i+"structures/update",headers:(0,r.Z)(),data:o};return a.Z.request(d)}getNewReportStructures(e){let t=JSON.stringify({oldReportStructureId:e}),s={method:"post",maxBodyLength:1/0,url:i+"structures/new/all",headers:(0,r.Z)(),data:t};return a.Z.request(s)}createReport(e,t,s,l,n,o){let d=JSON.stringify({reportData:JSON.stringify(e),dateStart:t,dateEnd:s,reportStructureId:l,userId:n,reportStatusId:o}),u={method:"post",maxBodyLength:1/0,url:i+"create",headers:(0,r.Z)(),data:d};return a.Z.request(u)}updateReport(e,t,s,l,n,o,d){let u=JSON.stringify({reportId:e,reportData:JSON.stringify(t),dateStart:s,dateEnd:l,reportStructureId:n,userId:o,reportStatusId:d}),c={method:"post",maxBodyLength:1/0,url:i+"update",headers:(0,r.Z)(),data:u};return a.Z.request(c)}updateReportStatus(e,t){let s=JSON.stringify({reportId:e,statusId:t}),l={method:"post",maxBodyLength:1/0,url:i+"status/update",headers:(0,r.Z)(),data:s};return a.Z.request(l)}updateReportChange(e,t){let s=JSON.stringify({reportId:e,canChange:t}),l={method:"post",maxBodyLength:1/0,url:i+"change/update",headers:(0,r.Z)(),data:s};return a.Z.request(l)}deleteReport(e){return a.Z["delete"](i+"delete/"+e,{headers:(0,r.Z)()})}updateReportType(e,t){let s=JSON.stringify({reportTypeName:t}),l={method:"post",maxBodyLength:1/0,url:i+"types/update/"+e,headers:(0,r.Z)(),data:s};return a.Z.request(l)}}t["Z"]=new l},8310:function(e,t,s){s.d(t,{Z:function(){return D}});var a=s(3396),r=s(7139);const i={class:"input-field col s12 selectSingle w-full"},l=["multiple","disabled"],n=["selected"],o=["value","selected"],d=["multiple","disabled"],u=["selected"],c=["value","selected"],p=["multiple","disabled"],h=["selected"],g=["value","selected"],m=["multiple","disabled"],y=["selected"],f=["value","selected"],x={class:"opacity-0"},w=["multiple","disabled"],k=["selected"],b=["value","selected"],C={class:"opacity-0"};function v(e,t,s,v,S,_){return(0,a.wg)(),(0,a.iD)("div",i,["filter"===this.type?((0,a.wg)(),(0,a.iD)("select",{key:0,multiple:this.multy,disabled:this.disabled},[(0,a._)("option",{value:"",selected:this.selected},(0,r.zw)(s.message),9,n),((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(this.content,(e=>((0,a.wg)(),(0,a.iD)("option",{key:e,value:e,selected:e===this.equalId},(0,r.zw)(e),9,o)))),128))],8,l)):Array.isArray(this.equalId)||0===this.content.length||"object"===typeof this.content[0]?Array.isArray(this.equalId)&&0!==this.content.length&&"object"!==typeof this.content[0]?((0,a.wg)(),(0,a.iD)("select",{key:2,multiple:this.multy,disabled:this.disabled},[(0,a._)("option",{value:"",disabled:"",selected:this.selected},(0,r.zw)(s.message),9,h),((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(this.content,(e=>((0,a.wg)(),(0,a.iD)("option",{key:e,value:e,selected:this.isSelect(this.equalId,e)},(0,r.zw)(e),9,g)))),128))],8,p)):Array.isArray(this.equalId)?((0,a.wg)(),(0,a.iD)("select",{key:4,multiple:this.multy,disabled:this.disabled},[(0,a._)("option",{value:"",disabled:"",selected:this.selected},(0,r.zw)(s.message),9,k),((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(this.content,(e=>((0,a.wg)(),(0,a.iD)("option",{key:e.id,value:e.id,selected:this.isSelect(this.equalId,e.id)},[(0,a.Uk)((0,r.zw)(e.name)+" ",1),(0,a._)("span",C,"("+(0,r.zw)(e.id)+")",1)],8,b)))),128))],8,w)):((0,a.wg)(),(0,a.iD)("select",{key:3,multiple:this.multy,disabled:this.disabled},[(0,a._)("option",{value:"",disabled:"",selected:this.selected},(0,r.zw)(s.message),9,y),((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(this.content,(e=>((0,a.wg)(),(0,a.iD)("option",{key:e.id,value:e.id,selected:e.id===this.equalId},[(0,a.Uk)((0,r.zw)(e.name)+" ",1),(0,a._)("span",x,"("+(0,r.zw)(e.id)+")",1)],8,f)))),128))],8,m)):((0,a.wg)(),(0,a.iD)("select",{key:1,multiple:this.multy,disabled:this.disabled},[(0,a._)("option",{value:"",disabled:"",selected:this.selected},(0,r.zw)(s.message),9,u),((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(this.content,(e=>((0,a.wg)(),(0,a.iD)("option",{key:e,value:e,selected:e===this.equalId},(0,r.zw)(e),9,c)))),128))],8,d))])}s(7658);var S={name:"selectComponent",props:["selected","message","content","multy","disabled","equalId","type"],data:()=>({select:null}),mounted(){setTimeout((()=>{this.select=M.FormSelect.init(document.querySelectorAll("select"),{})}),0)},methods:{isSelect(e,t){let s=[];for(let a of e)s.push(Number(a));return s.includes(t)}},computed:{currentUser(){return this.$store.state.auth.user}},beforeDestroy(){this.select&&this.select.destroy&&this.select.destroy()}},_=s(89);const P=(0,_.Z)(S,[["render",v]]);var D=P},543:function(e,t,s){s.r(t),s.d(t,{default:function(){return re}});var a=s(3396),r=s(9242),i=s(7139);const l=(0,a._)("div",{class:"chapter-name text-3xl mb-6"}," Отчеты ",-1),n={class:"flex gap-x-5 flex-grow"},o={class:"menu w-1/5"},d={class:"w-4/5 flex flex-col mb-5"},u={class:"search flex gap-x-5 mb-6"},c={action:"",method:"get",class:"shadow-md rounded-xl flex items-stretch w-10/12 border"},p={class:"w-full relative input-field"},h=(0,a._)("svg",{xmlns:"http://www.w3.org/2000/svg",fill:"none",viewBox:"0 0 24 24","stroke-width":"1.5",stroke:"currentColor",class:"w-6 h-6"},[(0,a._)("path",{"stroke-linecap":"round","stroke-linejoin":"round",d:"M6 18L18 6M6 6l12 12"})],-1),g=[h],m=(0,a._)("svg",{xmlns:"http://www.w3.org/2000/svg",fill:"none",viewBox:"0 0 24 24","stroke-width":"1.5",stroke:"currentColor",class:"w-6 h-6 mr-1"},[(0,a._)("path",{"stroke-linecap":"round","stroke-linejoin":"round",d:"M12 3c2.755 0 5.455.232 8.083.678.533.09.917.556.917 1.096v1.044a2.25 2.25 0 01-.659 1.591l-5.432 5.432a2.25 2.25 0 00-.659 1.591v2.927a2.25 2.25 0 01-1.244 2.013L9.75 21v-6.568a2.25 2.25 0 00-.659-1.591L3.659 7.409A2.25 2.25 0 013 5.818V4.774c0-.54.384-1.006.917-1.096A48.32 48.32 0 0112 3z"})],-1),y={class:"clear-filter mb-5 flex justify-end"},f=(0,a._)("svg",{class:"w-6 h-6 mr-1",viewBox:"0 0 24 24",stroke:"currentColor",fill:"none",xmlns:"http://www.w3.org/2000/svg"},[(0,a._)("g",{id:"Interface / Filter_Off"},[(0,a._)("path",{id:"Vector",d:"M13 4H18.4C18.9601 4 19.2409 4 19.4548 4.10899C19.6429 4.20487 19.7948 4.35774 19.8906 4.5459C19.9996 4.75981 20 5.04005 20 5.6001V6.3448C20 6.58444 20 6.70551 19.9727 6.81942C19.9482 6.92146 19.9072 7.01893 19.8524 7.1084C19.7906 7.20931 19.7043 7.2958 19.5314 7.46875L18 9.00012M7.49961 4H5.59961C5.03956 4 4.75981 4 4.5459 4.10899C4.35774 4.20487 4.20487 4.35774 4.10899 4.5459C4 4.75981 4 5.04005 4 5.6001V6.33736C4 6.58195 4 6.70433 4.02763 6.81942C4.05213 6.92146 4.09263 7.01893 4.14746 7.1084C4.20928 7.20928 4.29591 7.29591 4.46875 7.46875L9.53149 12.5315C9.70443 12.7044 9.79044 12.7904 9.85228 12.8914C9.90711 12.9808 9.94816 13.0786 9.97266 13.1807C10 13.2946 10 13.4155 10 13.6552V18.411C10 19.2682 10 19.6971 10.1805 19.9552C10.3382 20.1806 10.5814 20.331 10.8535 20.3712C11.1651 20.4172 11.5487 20.2257 12.3154 19.8424L13.1154 19.4424C13.4365 19.2819 13.5966 19.2013 13.7139 19.0815C13.8176 18.9756 13.897 18.8485 13.9453 18.7083C14 18.5499 14 18.37 14 18.011V13.6626C14 13.418 14 13.2958 14.0276 13.1807C14.0521 13.0786 14.0926 12.9809 14.1475 12.8915C14.2091 12.7909 14.2952 12.7048 14.4669 12.5331L14.4688 12.5314L15.5001 11.5001M15.5001 11.5001L5 1M15.5001 11.5001L19 15","stroke-width":"2","stroke-linecap":"round","stroke-linejoin":"round"})])],-1),x={class:"filter-item mb-5"},w={class:"flex gap-5"},k=(0,a._)("div",{class:"whitespace-nowrap self-end text-base"},"Период дат с",-1),b=["max"],C=(0,a._)("div",{class:"whitespace-nowrap self-end text-base"},"по",-1),v=["min"],S=(0,a._)("div",{class:"whitespace-nowrap self-end text-sm"},"(не включительно)",-1),_={class:"filter-item mb-5 flex gap-5"},P={class:"filter-item-inner w-1/2"},D=(0,a._)("div",{class:"filter-item-header mb-1"}," Сортировка по полю: ",-1),L={class:"filter-item-body"},T={class:"filter-item-inner w-1/2"},Z=(0,a._)("div",{class:"filter-item-header mb-1"}," Сортировка: ",-1),V={class:"filter-item-body"},q={class:"filter-item mb-5 flex gap-5"},M={class:"filter-item-inner w-1/2"},N=(0,a._)("div",{class:"filter-item-header mb-1"}," Статус отчета: ",-1),R={class:"filter-item-body"},B={class:"filter-item-inner w-1/2"},F=(0,a._)("div",{class:"filter-item-header mb-1"}," Тип отчета: ",-1),H={class:"filter-item-body"},I={class:"main-table-menu pb-6 bg-white relative z-10"},K={class:"main-table-list flex flex-row-reverse"},z={class:"main-table-item"},A=(0,a._)("svg",{xmlns:"http://www.w3.org/2000/svg",fill:"none",viewBox:"0 0 24 24","stroke-width":"1.5",stroke:"currentColor",class:"w-6 h-6 mr-1"},[(0,a._)("path",{"stroke-linecap":"round","stroke-linejoin":"round",d:"M12 4.5v15m7.5-7.5h-15"})],-1),O={class:"main-table-item"},$=(0,a._)("svg",{xmlns:"http://www.w3.org/2000/svg",fill:"none",viewBox:"0 0 24 24","stroke-width":"1.5",stroke:"currentColor",class:"w-6 h-6 mr-1"},[(0,a._)("path",{"stroke-linecap":"round","stroke-linejoin":"round",d:"M16.023 9.348h4.992v-.001M2.985 19.644v-4.992m0 0h4.992m-4.993 0l3.181 3.183a8.25 8.25 0 0013.803-3.7M4.031 9.865a8.25 8.25 0 0113.803-3.7l3.181 3.182m0-4.991v4.99"})],-1),J={key:1,class:"bg-white message px-3 py-6 text-center text-yellow-600 rounded-lg border-yellow-600 border-2 mb-6 text-lg"},j={class:"flex"},U={class:""};function Y(e,t,s,h,Y,W){const E=(0,a.up)("Sidebar"),G=(0,a.up)("SelectVue"),Q=(0,a.up)("router-link"),X=(0,a.up)("Loader"),ee=(0,a.up)("MainTable"),te=(0,a.up)("Paginate");return(0,a.wg)(),(0,a.iD)(a.HY,null,[l,(0,a._)("div",n,[(0,a._)("div",o,[(0,a.Wm)(E)]),(0,a._)("div",d,[(0,a._)("div",u,[(0,a._)("form",c,[(0,a._)("div",p,[(0,a.wy)((0,a._)("input",{"onUpdate:modelValue":t[0]||(t[0]=e=>this.parameter=e),maxlength:"255",type:"text",name:"search-text",id:"",placeholder:"Поиск",class:"w-full rounded-l-xl py-3 pl-5 pr-10 block flex-grow shrink focus:outline-none focus:ring-2 focus:ring-[var(--color-main)]"},null,512),[[r.nr,this.parameter]]),(0,a._)("div",{onClick:t[1]||(t[1]=(0,r.iM)((e=>this.parameter=""),["prevent"])),class:"cursor-pointer px-2 py-3 absolute right-0 top-0"},g)]),(0,a._)("button",{onClick:t[2]||(t[2]=(0,r.iM)(((...e)=>W.refresh&&W.refresh(...e)),["prevent"])),type:"submit",class:"main-btn-invers block shrink px-7 rounded-r-xl"},"Найти")]),(0,a._)("button",{onClick:t[3]||(t[3]=(0,r.iM)((e=>this.showFilter=!this.showFilter),["prevent"])),href:"#",class:"main-btn-invers rounded-xl w-2/12 block text-center py-3 flex justify-center"},[m,(0,a.Uk)(" Фильтры ")])]),(0,a._)("div",{class:"filter shadow-md rounded-xl p-5 mb-6 border",ref:"filter",style:(0,i.j5)(this.style)},[(0,a._)("div",y,[(0,a._)("button",{onClick:t[4]||(t[4]=(0,r.iM)((e=>{this.minValueDatePole="",this.maxValueDatePole="",this.resetKey+=1,this.reportTypeKey+=1}),["prevent"])),class:"main-btn px-3 py-2 flex"},[f,(0,a.Uk)(" Сбросить фильтры ")])]),(0,a._)("div",x,[(0,a._)("div",w,[k,(0,a.wy)((0,a._)("input",{type:"date","onUpdate:modelValue":t[5]||(t[5]=t=>e.minValueDatePole=t),max:this.maxValueDatePole,class:"input-text status-data-menu-date-input"},null,8,b),[[r.nr,e.minValueDatePole]]),C,(0,a.wy)((0,a._)("input",{type:"date","onUpdate:modelValue":t[6]||(t[6]=t=>e.maxValueDatePole=t),min:this.minValueDatePole,class:"input-text status-data-menu-date-input"},null,8,v),[[r.nr,e.maxValueDatePole]]),S])]),(0,a._)("div",_,[(0,a._)("div",P,[D,(0,a._)("div",L,[((0,a.wg)(),(0,a.j4)(G,{class:"search-field",selected:!0,message:"Выберите параметр сортировки",content:["Номер отчета","Сотрудники","Стаутс отчета","По шаблону отчета"],key:this.resetKey}))])]),(0,a._)("div",T,[Z,(0,a._)("div",V,[((0,a.wg)(),(0,a.j4)(G,{class:"search-dir",message:"Выберите тип сортировки",content:["По убыванию","По возрастанию"],key:this.resetKey}))])])]),(0,a._)("div",q,[(0,a._)("div",M,[N,(0,a._)("div",R,[((0,a.wg)(),(0,a.j4)(G,{class:"search-status",type:"filter",selected:!0,message:"Выберите статус отчета",content:["Черновик","Готов","Архив"],key:this.resetKey}))])]),(0,a._)("div",B,[F,(0,a._)("div",H,[((0,a.wg)(),(0,a.j4)(G,{class:"search-type",type:"filter",selected:!0,message:"Выберите тип отчета",content:this.reportTypes,key:e.reportTypeKey},null,8,["content"]))])])])],4),(0,a._)("div",I,[(0,a._)("ul",K,[(0,a._)("li",z,[(0,a.Wm)(Q,{to:"/report",class:"main-btn py-3 px-5 flex",disabled:0===this.listReportStructures.length},{default:(0,a.w5)((()=>[A,(0,a.Uk)(" Создать ")])),_:1},8,["disabled"])]),(0,a._)("li",O,[(0,a._)("button",{class:"main-btn py-3 px-5 flex",onClick:t[7]||(t[7]=(0,r.iM)(((...e)=>W.refresh&&W.refresh(...e)),["prevent"]))},[$,(0,a.Uk)(" Обновить ")])])])]),this.loading?((0,a.wg)(),(0,a.j4)(X,{key:0,class:"bg-white"})):(0,a.kq)("",!0),this.loading||0!==this.listReportStructures.length?(0,a.kq)("",!0):((0,a.wg)(),(0,a.iD)("div",J,"Невозможно создать отчет, пока нет шаблонов отчетов, обратитесь к техническому администратору.")),this.loading?(0,a.kq)("",!0):((0,a.wg)(),(0,a.j4)(ee,{key:2,class:"bg-white",header:e.header,content:e.content,noDataMessage:e.noDataMessage},null,8,["header","content","noDataMessage"])),(0,a._)("div",j,[(0,a._)("div",U," Всего найдено отчетов: "+(0,i.zw)(this.countReports),1),(0,a.Wm)(te,{"page-count":this.countPage,"click-handler":W.pageChangeHandler,"prev-text":"Назад","next-text":"Вперед","container-class":"pagination"},null,8,["page-count","click-handler"])])])])],64)}s(7658);var W=s(2435),E=s(6833),G=s(8310),Q=s(1352),X=s(6270),ee=s(4501),te={name:"ReportsView",data:()=>({showFilter:!1,loading:!0,content:[],header:["№ отчета","Наименование","Описание","Тип","Создал","Статус","Дата создания",""],noDataMessage:"По вашему запросу отчеты не найдены",message:"",listReportStructures:[],parameter:"",currentPage:1,sortField:"id",sortDir:"desc",countReports:0,countPage:0,reportTypes:[],reportTypeKey:1,minValueDatePole:"",maxValueDatePole:"",resetKey:1}),async mounted(){X.Z[this.$route.query.message]&&this.$message(X.Z[this.$route.query.message]),await Q.Z.getAllReportTypes().then((e=>{this.reportTypes=[];for(let t of e.data)this.reportTypes.push(t.name);this.reportTypeKey+=1}),(e=>{this.message=e.response&&e.response.data&&e.response.data.message||e.message||e.toString(),this.$error(X.Z[this.message]||"Что-то пошло не так")})),setTimeout((()=>{this.refresh(),this.loading=!1}),0)},methods:{async refresh(){this.loading=!0,this.content=[],this.sortField=document.querySelector(".search-field li.selected").innerText,"Выберите параметр сортировки"===this.sortField||"Номер отчета"===this.sortField?this.sortField="id":"Сотрудники"===this.sortField?this.sortField="user":"Стаутс отчета"===this.sortField?this.sortField="reportStatus":"По шаблону отчета"===this.sortField&&(this.sortField="reportStructure"),this.sortDir=document.querySelector(".search-dir li.selected").innerText,"По убыванию"===this.sortDir?this.sortDir="desc":this.sortDir="asc";let e=document.querySelector(".search-status li.selected").innerText;"Выберите статус отчета"===e&&(e="");let t=document.querySelector(".search-type li.selected").innerText;"Выберите тип отчета"===t&&(t=""),await Q.Z.getAllActiveReportStructures().then((e=>{this.listReportStructures=e.data}),(e=>{this.message=e.response&&e.response.data&&e.response.data.message||e.message||e.toString(),this.$error(X.Z[this.message]||"Что-то пошло не так")})),await Q.Z.getAllReports(this.parameter,this.currentPage,this.sortField,this.sortDir,e,this.minValueDatePole,this.maxValueDatePole,t).then((e=>{this.content=[],this.countReports=e.data.totalItems,this.countPage=e.data.totalPages;for(const t of e.data.reports)this.content.push([{type:"id",text:t.id},{type:t.reportStructure.name.length<=20?"onlyText":"text",text:t.reportStructure.name,shortTest:t.reportStructure.name.length<=20?t.reportStructure.name:t.reportStructure.name.substr(0,20)+"..."},{type:t.reportStructure.description.length<=30?"onlyText":"text",text:t.reportStructure.description,shortTest:t.reportStructure.description.length<=30?t.reportStructure.description:t.reportStructure.description.substr(0,30)+"..."},{type:t.reportStructure.reportType.name.length<=20?"onlyText":"text",text:t.reportStructure.reportType.name,shortTest:t.reportStructure.reportType.name.length<=20?t.reportStructure.reportType.name:t.reportStructure.reportType.name.substr(0,20)+"..."},{type:"text",text:t.user.lastName+" "+t.user.name+" "+t.user.patronymic,shortTest:t.user.id===this.$store.state.auth.user.id?"Вы":t.user.lastName+" "+t.user.name[0]+". "+t.user.patronymic[0]+"."},{type:"reportStatus",text:t.reportStatus.name},{type:"date",text:t.dateCreate},{type:"linkP",text:"Подробнее",link:"/report?reportId="+t.id}])}),(e=>{this.message=e.response&&e.response.data&&e.response.data.message||e.message||e.toString(),this.$error(X.Z[this.message]||"Что-то пошло не так")})),this.loading=!1},pageChangeHandler:async function(e){this.currentPage!==e&&(this.currentPage=e,await this.refresh())}},computed:{style(){return`height: ${this.showFilter?this.$refs.filter.scrollHeight+20:0}px; padding: ${this.showFilter?"1.25rem":"0px"}; border-width: ${this.showFilter?"1px":"0px"}; opacity: ${this.showFilter?1:0}; overflow: ${this.showFilter?"visible":"hidden"}; margin-bottom: ${this.showFilter?1.25:0}rem;`}},components:{Sidebar:W.Z,MainTable:E.Z,SelectVue:G.Z,Paginate:ee.Z}},se=s(89);const ae=(0,se.Z)(te,[["render",Y]]);var re=ae}}]);
//# sourceMappingURL=543.4480db30.js.map