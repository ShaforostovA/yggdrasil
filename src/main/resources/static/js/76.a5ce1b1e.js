"use strict";(self["webpackChunkyggdrasil_client"]=self["webpackChunkyggdrasil_client"]||[]).push([[76],{7882:function(e,t,r){var s=r(4161),a=r(2479);const n="http://localhost/api/v1/documents/";class u{getDocument(e){return s.Z.get(n+`${e}`,{headers:(0,a.Z)()})}getDocuments(e,t,r,u,o,d,i,m,l){let c=JSON.stringify({typeSearch:e,parameter:t,currentPage:r,sortField:u,sortDir:o,statusName:d,minDate:i,maxDate:m,documentType:l}),p={method:"post",maxBodyLength:1/0,url:n+"all",headers:(0,a.Z)(),data:c};return s.Z.request(p)}getDocumentStructures(){return s.Z.get(n+"structures/short",{headers:(0,a.Z)()})}getDocumentStructure(e){return s.Z.get(n+`structures/${e}`,{headers:(0,a.Z)()})}createDocument(e,t,r,u){let o=JSON.stringify({documentData:JSON.stringify(u),documentStatusId:Number(r),documentStructureId:Number(t),username:e}),d={method:"post",maxBodyLength:1/0,url:n+"create",headers:(0,a.Z)(),data:o};return s.Z.request(d)}deleteDocument(e){return s.Z["delete"](n+"delete/"+e,{headers:(0,a.Z)()})}updateDocumentStatus(e,t){let r=JSON.stringify({documentStatusId:JSON.stringify(t)}),u={method:"post",maxBodyLength:1/0,url:n+`${e}/status/update`,headers:(0,a.Z)(),data:r};return s.Z.request(u)}updateDocumentData(e,t){let r=JSON.stringify({documentData:JSON.stringify(t)}),u={method:"post",maxBodyLength:1/0,url:n+`${e}/data/update`,headers:(0,a.Z)(),data:r};return s.Z.request(u)}getUserStatistics(e,t,r){let u=JSON.stringify({minDate:t,maxDate:r}),o={method:"post",maxBodyLength:1/0,url:n+`statistic/user/${e}`,headers:(0,a.Z)(),data:u};return s.Z.request(o)}getDepartmentStatistics(e,t,r){let u=JSON.stringify({minDate:t,maxDate:r}),o={method:"post",maxBodyLength:1/0,url:n+`statistic/department/${e}`,headers:(0,a.Z)(),data:u};return s.Z.request(o)}getFacultayStatistics(e,t,r){let u=JSON.stringify({minDate:t,maxDate:r}),o={method:"post",maxBodyLength:1/0,url:n+`statistic/faculty/${e}`,headers:(0,a.Z)(),data:u};return s.Z.request(o)}getAllStatistics(e,t){let r=JSON.stringify({minDate:e,maxDate:t}),u={method:"post",maxBodyLength:1/0,url:n+"statistic/all",headers:(0,a.Z)(),data:r};return s.Z.request(u)}getAllDocumentTypes(){return s.Z.get(n+"types/all",{headers:(0,a.Z)()})}checkDocumentTypeName(e){let t=JSON.stringify({name:e}),r={method:"post",maxBodyLength:1/0,url:n+"types/check",headers:(0,a.Z)(),data:t};return s.Z.request(r)}createDocumentType(e){let t=JSON.stringify({name:e}),r={method:"post",maxBodyLength:1/0,url:n+"types/create",headers:(0,a.Z)(),data:t};return s.Z.request(r)}getAdminDocumentStructures(e,t,r,u,o,d,i,m){let l=JSON.stringify({parameter:e,currentPage:t,sortField:r,sortDir:u,isActive:o,minDate:d,maxDate:i,documentTypeName:m}),c={method:"post",maxBodyLength:1/0,url:n+"structures/short/admin",headers:(0,a.Z)(),data:l};return s.Z.request(c)}createDocumentStructure(e,t,r,u,o){let d=JSON.stringify({id:e,name:t,description:r,documentTypeId:u,structureData:JSON.stringify(o)}),i={method:"post",maxBodyLength:1/0,url:n+"structures/create",headers:(0,a.Z)(),data:d};return s.Z.request(i)}updateDocumentStructure(e,t,r,u,o){let d=JSON.stringify({id:e,name:t,description:r,documentTypeId:u,structureData:JSON.stringify(o)}),i={method:"post",maxBodyLength:1/0,url:n+"structures/update",headers:(0,a.Z)(),data:d};return s.Z.request(i)}updateStatusDocumentStructure(e,t){let r=JSON.stringify({id:e,isActive:Boolean(t)}),u={method:"post",maxBodyLength:1/0,url:n+"structures/status/update",headers:(0,a.Z)(),data:r};return s.Z.request(u)}checkDocumentStructure(e){return s.Z.get(n+`structures/${e}/check`,{headers:(0,a.Z)()})}updateStatusDocumentsByStructureId(e){let t=JSON.stringify({id:e}),r={method:"post",maxBodyLength:1/0,url:n+"status/update/remake",headers:(0,a.Z)(),data:t};return s.Z.request(r)}getNewDocumentStructures(e){let t=JSON.stringify({oldDocumentStructureId:e}),r={method:"post",maxBodyLength:1/0,url:n+"structures/new/all",headers:(0,a.Z)(),data:t};return s.Z.request(r)}getAllFacultyDocuments(e,t,r,u){let o=JSON.stringify({documentStructureId:e,facultyId:t,minDate:r,maxDate:u}),d={method:"post",maxBodyLength:1/0,url:n+"faculty/all",headers:(0,a.Z)(),data:o};return s.Z.request(d)}getAllDepartmentDocuments(e,t,r,u){let o=JSON.stringify({documentStructureId:e,departmentId:t,minDate:r,maxDate:u}),d={method:"post",maxBodyLength:1/0,url:n+"department/all",headers:(0,a.Z)(),data:o};return s.Z.request(d)}archivingDocuments(e,t,r,u){let o=JSON.stringify({documentStructureId:e,documentStatusId:t,minDate:r,maxDate:u}),d={method:"post",maxBodyLength:1/0,url:n+"archiving",headers:(0,a.Z)(),data:o};return s.Z.request(d)}documentExportToExcel(e,t,r,u,o){let d=JSON.stringify({documentStructures:e,facultyId:t,departmentId:r,minDate:u,maxDate:o}),i={method:"post",maxBodyLength:1/0,url:n+"export",headers:(0,a.Z)(),responseType:"blob",data:d};return s.Z.request(i)}documentCombinedExportToExcel(e,t,r,u,o){let d=JSON.stringify({documentStructures:e,facultyList:t,departmentList:r,minDate:u,maxDate:o}),i={method:"post",maxBodyLength:1/0,url:n+"combined/export",headers:(0,a.Z)(),responseType:"blob",data:d};return s.Z.request(i)}updateDocumentType(e,t){let r=JSON.stringify({documentTypeName:t}),u={method:"post",maxBodyLength:1/0,url:n+"types/update/"+e,headers:(0,a.Z)(),data:r};return s.Z.request(u)}}t["Z"]=new u},2435:function(e,t,r){r.d(t,{Z:function(){return l}});var s=r(3396),a=r(7139);const n={class:"shadow-md rounded-xl overflow-hidden border"};function u(e,t,r,u,o,d){const i=(0,s.up)("router-link");return(0,s.wg)(),(0,s.iD)("ul",n,[((0,s.wg)(!0),(0,s.iD)(s.HY,null,(0,s.Ko)(e.links,(e=>((0,s.wg)(),(0,s.iD)("li",{key:e.url},[e.roles.includes(this.currentUser.roles[0])?((0,s.wg)(),(0,s.j4)(i,{key:0,"active-class":"active",to:e.url,class:"hover:bg-gray-200 pl-5 py-2 block select-none"},{default:(0,s.w5)((()=>[(0,s.Uk)((0,a.zw)(e.title),1)])),_:2},1032,["to"])):(0,s.kq)("",!0)])))),128))])}var o=r(8806),d={data:()=>({links:[{title:"Документы",url:"/documents",roles:[o.v.User,o.v.Admin,o.v.Moderator]},{title:"Отчеты",url:"/reports",roles:[o.v.Admin,o.v.Moderator]},{title:"Факультеты",url:"/facultets",roles:[o.v.SuperAdmin]},{title:"Кафедры",url:"/departments",roles:[o.v.SuperAdmin]},{title:"Сотрудники",url:"/employees",roles:[o.v.SuperAdmin,o.v.Moderator]},{title:"Студенты",url:"/students",roles:[o.v.User,o.v.SuperAdmin,o.v.Moderator]},{title:"Конструктор шаблонов",url:"/constructors",roles:[o.v.SuperAdmin]},{title:"Статистика",url:"/statistics",roles:[o.v.User,o.v.Admin,o.v.Moderator]},{title:"Разное",url:"/different",roles:[o.v.SuperAdmin]}]}),computed:{currentUser(){return this.$store.state.auth.user}}},i=r(89);const m=(0,i.Z)(d,[["render",u]]);var l=m},1076:function(e,t,r){r.r(t),r.d(t,{default:function(){return O}});var s=r(3396),a=r(7139),n=r(9242);const u={class:"chapter-name text-3xl mb-6"},o={class:"flex gap-x-5"},d={class:"menu w-1/5"},i={class:"w-4/5"},m={key:1,class:"department-body mb-10"},l={class:"menu-top mb-6 flex justify-between"},c=(0,s._)("svg",{xmlns:"http://www.w3.org/2000/svg",fill:"none",viewBox:"0 0 24 24","stroke-width":"1.5",stroke:"currentColor",class:"w-6 h-6 mr-1"},[(0,s._)("path",{"stroke-linecap":"round","stroke-linejoin":"round",d:"M6.75 15.75L3 12m0 0l3.75-3.75M3 12h18"})],-1),p={class:"shadow-md rounded-xl border p-5"},h={class:"department-data-input"},g=(0,s._)("label",{for:"",class:"relative required"},"Название ключевого слова:",-1),y=["disabled"],D={class:"department-bottom-menu flex justify-end gap-5"},x=(0,s._)("svg",{xmlns:"http://www.w3.org/2000/svg",fill:"none",viewBox:"0 0 24 24","stroke-width":"1.5",stroke:"currentColor",class:"w-6 h-6 mr-1"},[(0,s._)("path",{"stroke-linecap":"round","stroke-linejoin":"round",d:"M16.862 4.487l1.687-1.688a1.875 1.875 0 112.652 2.652L6.832 19.82a4.5 4.5 0 01-1.897 1.13l-2.685.8.8-2.685a4.5 4.5 0 011.13-1.897L16.863 4.487zm0 0L19.5 7.125"})],-1),f=(0,s._)("svg",{xmlns:"http://www.w3.org/2000/svg",fill:"none",viewBox:"0 0 24 24","stroke-width":"1.5",stroke:"currentColor",class:"w-6 h-6 mr-1"},[(0,s._)("path",{"stroke-linecap":"round","stroke-linejoin":"round",d:"M9 15L3 9m0 0l6-6M3 9h12a6 6 0 010 12h-3"})],-1),Z={key:2,class:"main-btn px-5 py-3 text-xl inline-flex items-center",type:"submit"},S=(0,s._)("svg",{xmlns:"http://www.w3.org/2000/svg",fill:"none",viewBox:"0 0 24 24","stroke-width":"1.5",stroke:"currentColor",class:"w-6 h-6 mr-1"},[(0,s._)("path",{"stroke-linecap":"round","stroke-linejoin":"round",d:"M3 16.5v2.25A2.25 2.25 0 005.25 21h13.5A2.25 2.25 0 0021 18.75V16.5M16.5 12L12 16.5m0 0L7.5 12m4.5 4.5V3"})],-1);function v(e,t,r,v,w,k){const b=(0,s.up)("Sidebar"),N=(0,s.up)("Loader");return(0,s.wg)(),(0,s.iD)(s.HY,null,[(0,s._)("div",u," Кафедра — "+(0,a.zw)(this.documentTypeId),1),(0,s._)("div",o,[(0,s._)("div",d,[(0,s.Wm)(b)]),(0,s._)("div",i,[this.loading?((0,s.wg)(),(0,s.j4)(N,{key:0})):((0,s.wg)(),(0,s.iD)("div",m,[(0,s._)("div",l,[(0,s._)("button",{onClick:t[0]||(t[0]=(0,n.iM)(((...e)=>this.back&&this.back(...e)),["prevent"])),class:"main-btn py-3 px-5 inline-flex"},[c,(0,s.Uk)(" Вернуться ")])]),(0,s._)("div",p,[(0,s._)("form",{action:"",class:"department-form",onSubmit:t[4]||(t[4]=(0,n.iM)(((...e)=>this.updateDocumentType&&this.updateDocumentType(...e)),["prevent"]))},[(0,s._)("div",h,[g,(0,s.wy)((0,s._)("input",{"onUpdate:modelValue":t[1]||(t[1]=t=>e.documentTypeName=t),minlength:"1",required:"",type:"text",name:"",id:"",class:"input-text",placeholder:"Название ключевого слова",title:"Введите название ключевого слова",disabled:!this.updateMode},null,8,y),[[n.nr,e.documentTypeName,void 0,{trim:!0}]])]),(0,s._)("div",D,[this.updateMode?this.updateMode?((0,s.wg)(),(0,s.iD)("button",{key:1,class:"main-btn-red px-5 py-3 text-xl inline-flex items-center",onClick:t[3]||(t[3]=(0,n.iM)(((...e)=>k.rajectChanges&&k.rajectChanges(...e)),["prevent"]))},[f,(0,s.Uk)(" Отменить ")])):(0,s.kq)("",!0):((0,s.wg)(),(0,s.iD)("button",{key:0,class:"main-btn px-5 py-3 text-xl inline-flex items-center",onClick:t[2]||(t[2]=(0,n.iM)(((...e)=>k.changeOperationMode&&k.changeOperationMode(...e)),["prevent"]))},[x,(0,s.Uk)(" Редактировать ")])),this.updateMode?((0,s.wg)(),(0,s.iD)("button",Z,[S,(0,s.Uk)(" Сохранить ")])):(0,s.kq)("",!0)])],32)])]))])])],64)}var w=r(2435),k=r(6270),b=r(7882),N={name:"DocumentTypeView",props:["documentTypeId"],data:()=>({loading:!0,documentTypeName:"",updateMode:!1}),async mounted(){k.Z[this.$route.query.message]&&this.$message(k.Z[this.$route.query.message]),await this.findDocumentType(),setTimeout((()=>{}),0),this.loading=!1},methods:{async findDocumentType(){await b.Z.getAllDocumentTypes().then((e=>{for(let t of e.data)if(Number(t.id)===Number(this.documentTypeId)){this.documentTypeName=t.name;break}}),(e=>{this.message=e.response&&e.response.data&&e.response.data.message||e.message||e.toString(),this.$error(k.Z[this.message]||"Что-то пошло не так")}))},changeOperationMode(){this.updateMode=!0,this.$message("Включен режим редактирования")},async rajectChanges(){this.updateMode=!1,await this.findDocumentType(),this.$message("Отмена изменений")},async updateDocumentType(){await b.Z.updateDocumentType(this.documentTypeId,this.documentTypeName).then((e=>{this.updateMode=!1,this.$message("Тип документа обновлен")}),(e=>{this.message=e.response&&e.response.data&&e.response.data.message||e.message||e.toString(),this.$error(k.Z[this.message]||"Что-то пошло не так")}))},back(){this.$router.go(-1)}},beforeDestroy(){},components:{Sidebar:w.Z}},L=r(89);const T=(0,L.Z)(N,[["render",v]]);var O=T}}]);
//# sourceMappingURL=76.a5ce1b1e.js.map