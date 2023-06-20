"use strict";(self["webpackChunkyggdrasil_client"]=self["webpackChunkyggdrasil_client"]||[]).push([[890],{573:function(e,t,s){var a=s(4161),i=s(2479);s(3456);const l="http://10.100.124.9/api/v1/department/";class r{getUserDepartmentAllUsers(e){return a.Z.get(l+`users/list/${e}`,{headers:(0,i.Z)()})}getDepartmentsAll(){return a.Z.get(l+"all",{headers:(0,i.Z)()})}getDepartmentsAllSearch(e,t,s,r,d,n,o,c){let p=JSON.stringify({parameter:e,currentPage:t,sortField:s,sortDir:r,facultyName:d,isActive:n,minDate:o,maxDate:c}),u={method:"post",maxBodyLength:1/0,url:l+"search/all",headers:(0,i.Z)(),data:p};return a.Z.request(u)}getDepartmentsActiveAll(){return a.Z.get(l+"active/all",{headers:(0,i.Z)()})}updateDepartment(e,t,s,r,d){let n=JSON.stringify({id:e,shortName:t,name:s,description:r,facultyId:Number(d)}),o={method:"post",maxBodyLength:1/0,url:l+"update",headers:(0,i.Z)(),data:n};return a.Z.request(o)}createDepartment(e,t,s,r,d){let n=JSON.stringify({id:e,shortName:t,name:s,description:r,facultyId:Number(d)}),o={method:"post",maxBodyLength:1/0,url:l+"create",headers:(0,i.Z)(),data:n};return a.Z.request(o)}}t["Z"]=new r},5753:function(e,t,s){var a=s(4161),i=s(2479),l=s(3456);const r="http://10.100.124.9/api/v1/user/";class d{getUserInfo(){return a.Z.get(r+this.currentUser().username,{headers:(0,i.Z)()})}getEmployeeInfo(e){return a.Z.get(r+`profile/${e}/info`,{headers:(0,i.Z)()})}getUserFaculty(e){return a.Z.get(r+`${e}/faculty`,{headers:(0,i.Z)()})}getUserDepartment(e){return a.Z.get(r+`${e}/department`,{headers:(0,i.Z)()})}userTokenCheck(){return console.log((0,i.Z)()),a.Z.get(r+"token/check",{headers:(0,i.Z)()})}getAllUsers(){return a.Z.get(r+"list",{headers:(0,i.Z)()})}getAllDepartmentUsers(){return a.Z.get(r+"department/list",{headers:(0,i.Z)()})}getAllUsersFind(e,t,s,l,d,n,o,c,p,u){let m=JSON.stringify({parameter:e,currentPage:t,sortField:s,sortDir:l,minDate:d,maxDate:n,departmentName:o,isState:c,userRole:p,isActive:u}),h={method:"post",maxBodyLength:1/0,url:r+"list/find",headers:(0,i.Z)(),data:m};return a.Z.request(h)}updateProfile(e,t,s,l,d,n,o,c,p,u,m,h,g,w,v){let f=JSON.stringify({id:e,phone:t,email:s,imgUrl:l,lastName:d,name:n,patronymic:o,jobTitle:c,academicTitle:p,academicDegree:u,orcid:m,spinCode:h,birthday:g,state:w,departmentId:v}),y={method:"post",maxBodyLength:1/0,url:r+"update",headers:(0,i.Z)(),data:f};return a.Z.request(y)}checkValidUsername(e){let t=JSON.stringify({username:e}),s={method:"post",maxBodyLength:1/0,url:r+"username/check",headers:(0,i.Z)(),data:t};return a.Z.request(s)}createUser(e,t,s,l,d,n,o,c){let p=JSON.stringify({lastName:e,name:t,patronymic:s,state:l,departmentId:Number(d),username:n,password:o,userRole:c}),u={method:"post",maxBodyLength:1/0,url:r+"create",headers:(0,i.Z)(),data:p};return a.Z.request(u)}updateUserStatus(e,t){let s=JSON.stringify({userId:Number(e),isActive:t}),l={method:"post",maxBodyLength:1/0,url:r+"status/update",headers:(0,i.Z)(),data:s};return a.Z.request(l)}updateUserPassword(e,t){let s=JSON.stringify({userId:Number(e),password:t}),l={method:"post",maxBodyLength:1/0,url:r+"password/update",headers:(0,i.Z)(),data:s};return a.Z.request(l)}currentUser(){return l.Z.state.auth.user}}t["Z"]=new d},8310:function(e,t,s){s.d(t,{Z:function(){return C}});var a=s(3396),i=s(7139);const l={class:"input-field col s12 selectSingle w-full"},r=["multiple","disabled"],d=["selected"],n=["value","selected"],o=["multiple","disabled"],c=["selected"],p=["value","selected"],u=["multiple","disabled"],m=["selected"],h=["value","selected"],g=["multiple","disabled"],w=["selected"],v=["value","selected"],f={class:"opacity-0"},y=["multiple","disabled"],b=["selected"],x=["value","selected"],k={class:"opacity-0"};function _(e,t,s,_,M,U){return(0,a.wg)(),(0,a.iD)("div",l,["filter"===this.type?((0,a.wg)(),(0,a.iD)("select",{key:0,multiple:this.multy,disabled:this.disabled},[(0,a._)("option",{value:"",selected:this.selected},(0,i.zw)(s.message),9,d),((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(this.content,(e=>((0,a.wg)(),(0,a.iD)("option",{key:e,value:e,selected:e===this.equalId},(0,i.zw)(e),9,n)))),128))],8,r)):Array.isArray(this.equalId)||0===this.content.length||"object"===typeof this.content[0]?Array.isArray(this.equalId)&&0!==this.content.length&&"object"!==typeof this.content[0]?((0,a.wg)(),(0,a.iD)("select",{key:2,multiple:this.multy,disabled:this.disabled},[(0,a._)("option",{value:"",disabled:"",selected:this.selected},(0,i.zw)(s.message),9,m),((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(this.content,(e=>((0,a.wg)(),(0,a.iD)("option",{key:e,value:e,selected:this.isSelect(this.equalId,e)},(0,i.zw)(e),9,h)))),128))],8,u)):Array.isArray(this.equalId)?((0,a.wg)(),(0,a.iD)("select",{key:4,multiple:this.multy,disabled:this.disabled},[(0,a._)("option",{value:"",disabled:"",selected:this.selected},(0,i.zw)(s.message),9,b),((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(this.content,(e=>((0,a.wg)(),(0,a.iD)("option",{key:e.id,value:e.id,selected:this.isSelect(this.equalId,e.id)},[(0,a.Uk)((0,i.zw)(e.name)+" ",1),(0,a._)("span",k,"("+(0,i.zw)(e.id)+")",1)],8,x)))),128))],8,y)):((0,a.wg)(),(0,a.iD)("select",{key:3,multiple:this.multy,disabled:this.disabled},[(0,a._)("option",{value:"",disabled:"",selected:this.selected},(0,i.zw)(s.message),9,w),((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(this.content,(e=>((0,a.wg)(),(0,a.iD)("option",{key:e.id,value:e.id,selected:e.id===this.equalId},[(0,a.Uk)((0,i.zw)(e.name)+" ",1),(0,a._)("span",f,"("+(0,i.zw)(e.id)+")",1)],8,v)))),128))],8,g)):((0,a.wg)(),(0,a.iD)("select",{key:1,multiple:this.multy,disabled:this.disabled},[(0,a._)("option",{value:"",disabled:"",selected:this.selected},(0,i.zw)(s.message),9,c),((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(this.content,(e=>((0,a.wg)(),(0,a.iD)("option",{key:e,value:e,selected:e===this.equalId},(0,i.zw)(e),9,p)))),128))],8,o))])}s(7658);var U={name:"selectComponent",props:["selected","message","content","multy","disabled","equalId","type"],data:()=>({select:null}),mounted(){setTimeout((()=>{this.select=M.FormSelect.init(document.querySelectorAll("select"),{})}),0)},methods:{isSelect(e,t){let s=[];for(let a of e)s.push(Number(a));return s.includes(t)}},computed:{currentUser(){return this.$store.state.auth.user}},beforeDestroy(){this.select&&this.select.destroy&&this.select.destroy()}},S=s(89);const D=(0,S.Z)(U,[["render",_]]);var C=D},2435:function(e,t,s){s.d(t,{Z:function(){return p}});var a=s(3396),i=s(7139);const l={class:"shadow-md rounded-xl overflow-hidden border"};function r(e,t,s,r,d,n){const o=(0,a.up)("router-link");return(0,a.wg)(),(0,a.iD)("ul",l,[((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(e.links,(e=>((0,a.wg)(),(0,a.iD)("li",{key:e.url},[e.roles.includes(this.currentUser.roles[0])?((0,a.wg)(),(0,a.j4)(o,{key:0,"active-class":"active",to:e.url,class:"hover:bg-gray-200 pl-5 py-2 block select-none"},{default:(0,a.w5)((()=>[(0,a.Uk)((0,i.zw)(e.title),1)])),_:2},1032,["to"])):(0,a.kq)("",!0)])))),128))])}var d=s(8806),n={data:()=>({links:[{title:"Документы",url:"/documents",roles:[d.v.User,d.v.Admin,d.v.Moderator]},{title:"Отчеты",url:"/reports",roles:[d.v.Admin,d.v.Moderator]},{title:"Факультеты",url:"/facultets",roles:[d.v.SuperAdmin]},{title:"Кафедры",url:"/departments",roles:[d.v.SuperAdmin]},{title:"Сотрудники",url:"/employees",roles:[d.v.SuperAdmin,d.v.Moderator]},{title:"Студенты",url:"/students",roles:[d.v.User,d.v.SuperAdmin,d.v.Moderator]},{title:"Конструктор шаблонов",url:"/constructors",roles:[d.v.SuperAdmin]},{title:"Статистика",url:"/statistics",roles:[d.v.User,d.v.Admin,d.v.Moderator]},{title:"Разное",url:"/different",roles:[d.v.SuperAdmin]}]}),computed:{currentUser(){return this.$store.state.auth.user}}},o=s(89);const c=(0,o.Z)(n,[["render",r]]);var p=c},2890:function(e,t,s){s.r(t),s.d(t,{default:function(){return kt}});var a=s(3396),i=s(7139),l=s(9242);const r={class:"chapter-name text-3xl mb-6"},d={class:"flex gap-x-5 mb-6"},n={class:"menu w-1/5"},o={class:"w-4/5"},c={key:1,class:"menu-top mb-6 flex justify-between"},p=(0,a._)("svg",{xmlns:"http://www.w3.org/2000/svg",fill:"none",viewBox:"0 0 24 24","stroke-width":"1.5",stroke:"currentColor",class:"w-6 h-6 mr-1"},[(0,a._)("path",{"stroke-linecap":"round","stroke-linejoin":"round",d:"M6.75 15.75L3 12m0 0l3.75-3.75M3 12h18"})],-1),u=(0,a._)("svg",{xmlns:"http://www.w3.org/2000/svg",fill:"none",viewBox:"0 0 24 24","stroke-width":"1.5",stroke:"currentColor",class:"w-6 h-6 mr-1"},[(0,a._)("path",{"stroke-linecap":"round","stroke-linejoin":"round",d:"M21 7.5l-2.25-1.313M21 7.5v2.25m0-2.25l-2.25 1.313M3 7.5l2.25-1.313M3 7.5l2.25 1.313M3 7.5v2.25m9 3l2.25-1.313M12 12.75l-2.25-1.313M12 12.75V15m0 6.75l2.25-1.313M12 21.75V19.5m0 2.25l-2.25-1.313m0-16.875L12 2.25l2.25 1.313M21 14.25v2.25l-2.25 1.313m-13.5 0L3 16.5v-2.25"})],-1),m=(0,a._)("svg",{xmlns:"http://www.w3.org/2000/svg",fill:"none",viewBox:"0 0 24 24","stroke-width":"1.5",stroke:"currentColor",class:"w-6 h-6 mr-1"},[(0,a._)("path",{"stroke-linecap":"round","stroke-linejoin":"round",d:"M21 7.5l-9-5.25L3 7.5m18 0l-9 5.25m9-5.25v9l-9 5.25M3 7.5l9 5.25M3 7.5v9l9 5.25m0-9v9"})],-1),h={class:"faculty-data-top-menu flex justify-between mb-5"},g={class:"faculty-data-status"},w={key:0,class:"text-green-600"},v={key:1,class:"text-red-600"},f={class:"faculty-data-dates"},y={class:"faculty-data-dateCreate"},b={class:"faculty-data-dateUpdate"},x={class:"profile-data mb-1"},k={class:"profile-top-data-section flex gap-3"},_={class:"profile-left-data w-1/2 relative"},U=(0,a._)("div",{class:"profile-img text-[var(--color-main)]"},[(0,a._)("svg",{xmlns:"http://www.w3.org/2000/svg",fill:"none",viewBox:"0 0 24 24","stroke-width":"1.5",stroke:"currentColor",class:"w-72 h-72 mx-auto my-7"},[(0,a._)("path",{"stroke-linecap":"round","stroke-linejoin":"round",d:"M17.982 18.725A7.488 7.488 0 0012 15.75a7.488 7.488 0 00-5.982 2.975m11.963 0a9 9 0 10-11.963 0m11.963 0A8.966 8.966 0 0112 21a8.966 8.966 0 01-5.982-2.275M15 9.75a3 3 0 11-6 0 3 3 0 016 0z"})])],-1),S={class:"profile-state absolute left-0 top-0 text-[var(--color-main)] text-lg select-none"},D={class:"absolute left-0 bottom-3 text-lg"},C=(0,a._)("span",{class:"select-none font-bold"},"Логин: ",-1),Z={class:"profile-right-data profile-data-group flex flex-col w-1/2 justify-between relative z-10"},P={class:"profile-data-input"},q=(0,a._)("label",{for:"",class:"relative required"},"Фамилия:",-1),A=["disabled"],I={class:"profile-data-input"},L=(0,a._)("label",{for:"",class:"relative required"},"Имя:",-1),j=["disabled"],z={class:"profile-data-input"},$=(0,a._)("label",{for:"",class:"relative required"},"Отчество:",-1),N=["disabled"],T={class:"profile-data-input"},E=(0,a._)("label",{for:""},"Дата рождения:",-1),B=["disabled"],V={class:"profile-bottom-data-section"},O={class:"profile-data-input mb-2"},K=(0,a._)("label",{for:""},"Ученое звание:",-1),F=["disabled"],H={class:"profile-data-input mb-2"},J=(0,a._)("label",{for:""},"Ученая степень:",-1),R=["disabled"],Y={class:"profile-data-group flex gap-5"},W={class:"profile-data-input flex-grow"},G=(0,a._)("label",{for:""},"Email:",-1),Q=["disabled"],X={class:"profile-data-input flex-grow"},ee=(0,a._)("label",{for:""},"Телефон:",-1),te=["disabled"],se={class:"profile-data-group flex gap-5"},ae={class:"profile-data-input flex-grow"},ie=(0,a._)("label",{for:""},"Должность:",-1),le=["disabled"],re={class:"profile-data-input flex-grow"},de=(0,a._)("label",{for:""},"ORCID:",-1),ne=["disabled"],oe={class:"profile-data-input flex-grow"},ce=(0,a._)("label",{for:""},"Spin Code:",-1),pe=["disabled"],ue={class:"user-center-menu"},me=(0,a._)("div",{class:"menu-ling w-full h-1 rounded-lg bg-gray-300 block my-3"},null,-1),he={class:"user-center-menu-body"},ge={class:"menu-item flex gap-3 mb-3"},we=(0,a._)("div",{class:"profile-data-input menu-header"},[(0,a._)("label",{for:"",class:"relative required"},"Штатный сотрудник:")],-1),ve={class:"form-radio block gap-3 input-field flex"},fe={class:"block"},ye=["disabled","checked"],be=(0,a._)("span",null,"Да",-1),xe={class:"block"},ke=["disabled","checked"],_e=(0,a._)("span",null,"Нет",-1),Me={key:0,class:"menu-item"},Ue={class:"input-field col s12 department-data-input"},Se=(0,a._)("label",{for:"",class:"relative required"},"Выберите кафедру:",-1),De=(0,a._)("div",{class:"menu-ling w-full h-1 rounded-lg bg-gray-300 block my-5"},null,-1),Ce={class:"profile-ponel flex justify-between gap-5"},Ze={key:0,class:""},Pe=(0,a._)("svg",{xmlns:"http://www.w3.org/2000/svg",fill:"none",viewBox:"0 0 24 24","stroke-width":"1.5",stroke:"currentColor",class:"w-6 h-6"},[(0,a._)("path",{"stroke-linecap":"round","stroke-linejoin":"round",d:"M9 12.75L11.25 15 15 9.75m-3-7.036A11.959 11.959 0 013.598 6 11.99 11.99 0 003 9.749c0 5.592 3.824 10.29 9 11.623 5.176-1.332 9-6.03 9-11.622 0-1.31-.21-2.571-.598-3.751h-.152c-3.196 0-6.1-1.248-8.25-3.285z"})],-1),qe=(0,a._)("svg",{xmlns:"http://www.w3.org/2000/svg",fill:"none",viewBox:"0 0 24 24","stroke-width":"1.5",stroke:"currentColor",class:"w-6 h-6 mr-1"},[(0,a._)("path",{"stroke-linecap":"round","stroke-linejoin":"round",d:"M16.862 4.487l1.687-1.688a1.875 1.875 0 112.652 2.652L6.832 19.82a4.5 4.5 0 01-1.897 1.13l-2.685.8.8-2.685a4.5 4.5 0 011.13-1.897L16.863 4.487zm0 0L19.5 7.125"})],-1),Ae={key:3,class:"inline-flex gap-5 items-end"},Ie=(0,a._)("svg",{xmlns:"http://www.w3.org/2000/svg",fill:"none",viewBox:"0 0 24 24","stroke-width":"1.5",stroke:"currentColor",class:"w-6 h-6 mr-1"},[(0,a._)("path",{"stroke-linecap":"round","stroke-linejoin":"round",d:"M9 15L3 9m0 0l6-6M3 9h12a6 6 0 010 12h-3"})],-1),Le=(0,a._)("button",{class:"main-btn px-5 py-3 text-xl inline-flex items-center",type:"submit"},[(0,a._)("svg",{xmlns:"http://www.w3.org/2000/svg",fill:"none",viewBox:"0 0 24 24","stroke-width":"1.5",stroke:"currentColor",class:"w-6 h-6 mr-1"},[(0,a._)("path",{"stroke-linecap":"round","stroke-linejoin":"round",d:"M3 16.5v2.25A2.25 2.25 0 005.25 21h13.5A2.25 2.25 0 0021 18.75V16.5M16.5 12L12 16.5m0 0L7.5 12m4.5 4.5V3"})]),(0,a.Uk)(" Сохранить ")],-1),je={id:"updateProfile",class:"modal modal-fixed-footer modalUpdateProfile"},ze={class:"modal-content flex flex-col"},$e=(0,a._)("div",{class:"modal-img inline-block p-10 rounded-full border-[8px] border-solid border-red-700 text-red-700 mx-auto mb-5 font-bold"},[(0,a._)("svg",{xmlns:"http://www.w3.org/2000/svg",fill:"none",viewBox:"0 0 24 24","stroke-width":"1.5",stroke:"currentColor",class:"h-[8rem]"},[(0,a._)("path",{"stroke-linecap":"round","stroke-linejoin":"round",d:"M12 9v3.75m-9.303 3.376c-.866 1.5.217 3.374 1.948 3.374h14.71c1.73 0 2.813-1.874 1.948-3.374L13.949 3.378c-.866-1.5-3.032-1.5-3.898 0L2.697 16.126zM12 15.75h.007v.008H12v-.008z"})])],-1),Ne=(0,a._)("div",{class:"worn-text text-3xl text-red-700 mb-20 text-center"},"Внимание!",-1),Te={class:"text-2xl mb-3 text-center"},Ee={class:"modal-footer flex justify-evenly"},Be=(0,a._)("a",{href:"#",class:"modal-close main-btn-invers inline-block rounded-lg px-5 py-3 w-[150px] text-center text-xl"},"Отмена",-1),Ve={id:"cancelUpdateProfile",class:"modal modal-fixed-footer modalCancelUpdateProfile"},Oe={class:"modal-content flex flex-col"},Ke=(0,a._)("div",{class:"modal-img inline-block p-10 rounded-full border-[8px] border-solid border-red-700 text-red-700 mx-auto mb-5 font-bold"},[(0,a._)("svg",{xmlns:"http://www.w3.org/2000/svg",fill:"none",viewBox:"0 0 24 24","stroke-width":"1.5",stroke:"currentColor",class:"h-[8rem]"},[(0,a._)("path",{"stroke-linecap":"round","stroke-linejoin":"round",d:"M12 9v3.75m-9.303 3.376c-.866 1.5.217 3.374 1.948 3.374h14.71c1.73 0 2.813-1.874 1.948-3.374L13.949 3.378c-.866-1.5-3.032-1.5-3.898 0L2.697 16.126zM12 15.75h.007v.008H12v-.008z"})])],-1),Fe=(0,a._)("div",{class:"worn-text text-3xl text-red-700 mb-20 text-center"},"Внимание!",-1),He={class:"text-2xl mb-3 text-center"},Je={class:"modal-footer flex justify-evenly"},Re=(0,a._)("a",{href:"#",class:"modal-close main-btn-invers inline-block rounded-lg px-5 py-3 w-[150px] text-center text-xl"},"Отмена",-1),Ye={id:"updateUserPasswordModal",class:"modal modal-fixed-footer",ref:"updateUserPasswordModal"},We={class:"modal-content flex flex-col"},Ge={class:"profile-data-input mb-3"},Qe=(0,a._)("label",{for:"",class:"relative required"},"Новый пароль:",-1),Xe={class:"flex gap-3"},et={class:"w-full"},tt={key:0,class:"self-center"},st={key:0,class:"",title:"Пароль доступен."},at=(0,a._)("svg",{xmlns:"http://www.w3.org/2000/svg",viewBox:"0 0 24 24",fill:"currentColor",class:"w-6 h-6 text-green-600"},[(0,a._)("path",{"fill-rule":"evenodd",d:"M12 2.25c-5.385 0-9.75 4.365-9.75 9.75s4.365 9.75 9.75 9.75 9.75-4.365 9.75-9.75S17.385 2.25 12 2.25zm-2.625 6c-.54 0-.828.419-.936.634a1.96 1.96 0 00-.189.866c0 .298.059.605.189.866.108.215.395.634.936.634.54 0 .828-.419.936-.634.13-.26.189-.568.189-.866 0-.298-.059-.605-.189-.866-.108-.215-.395-.634-.936-.634zm4.314.634c.108-.215.395-.634.936-.634.54 0 .828.419.936.634.13.26.189.568.189.866 0 .298-.059.605-.189.866-.108.215-.395.634-.936.634-.54 0-.828-.419-.936-.634a1.96 1.96 0 01-.189-.866c0-.298.059-.605.189-.866zm2.023 6.828a.75.75 0 10-1.06-1.06 3.75 3.75 0 01-5.304 0 .75.75 0 00-1.06 1.06 5.25 5.25 0 007.424 0z","clip-rule":"evenodd"})],-1),it=[at],lt={key:1,class:"",title:"Пароль должен быть длиной от 6!"},rt=(0,a._)("svg",{xmlns:"http://www.w3.org/2000/svg",viewBox:"0 0 24 24",fill:"currentColor",class:"w-6 h-6 text-red-700"},[(0,a._)("path",{"fill-rule":"evenodd",d:"M12 2.25c-5.385 0-9.75 4.365-9.75 9.75s4.365 9.75 9.75 9.75 9.75-4.365 9.75-9.75S17.385 2.25 12 2.25zm-2.625 6c-.54 0-.828.419-.936.634a1.96 1.96 0 00-.189.866c0 .298.059.605.189.866.108.215.395.634.936.634.54 0 .828-.419.936-.634.13-.26.189-.568.189-.866 0-.298-.059-.605-.189-.866-.108-.215-.395-.634-.936-.634zm4.314.634c.108-.215.395-.634.936-.634.54 0 .828.419.936.634.13.26.189.568.189.866 0 .298-.059.605-.189.866-.108.215-.395.634-.936.634-.54 0-.828-.419-.936-.634a1.96 1.96 0 01-.189-.866c0-.298.059-.605.189-.866zm-4.34 7.964a.75.75 0 01-1.061-1.06 5.236 5.236 0 013.73-1.538 5.236 5.236 0 013.695 1.538.75.75 0 11-1.061 1.06 3.736 3.736 0 00-2.639-1.098 3.736 3.736 0 00-2.664 1.098z","clip-rule":"evenodd"})],-1),dt=[rt],nt={class:"modal-footer flex justify-evenly"},ot=(0,a._)("a",{href:"#",class:"modal-close main-btn-invers inline-block rounded-lg px-5 py-3 w-[150px] text-center text-xl"},"Отменить",-1),ct=["disabled"];function pt(e,t,s,M,at,rt){const pt=(0,a.up)("Sidebar"),ut=(0,a.up)("Loader"),mt=(0,a.up)("SelectVue");return(0,a.wg)(),(0,a.iD)(a.HY,null,[(0,a._)("div",r," Сотрудник — №"+(0,i.zw)(this.userId),1),(0,a._)("div",d,[(0,a._)("div",n,[(0,a.Wm)(pt)]),(0,a._)("div",o,[this.loading?((0,a.wg)(),(0,a.j4)(ut,{key:0})):(0,a.kq)("",!0),this.loading?(0,a.kq)("",!0):((0,a.wg)(),(0,a.iD)("div",c,[(0,a._)("button",{onClick:t[0]||(t[0]=(0,l.iM)(((...e)=>rt.back&&rt.back(...e)),["prevent"])),class:"main-btn py-3 px-5 inline-flex"},[p,(0,a.Uk)(" Вернуться ")]),!this.updateMode&&this.isActive?((0,a.wg)(),(0,a.iD)("button",{key:0,class:"main-btn-yellow px-5 py-3 text-lg flex items-center gap-1",onClick:t[1]||(t[1]=(0,l.iM)((e=>rt.updateEmployeeStatus(!1)),["prevent"]))},[u,(0,a.Uk)(" Деактивировать ")])):(0,a.kq)("",!0),this.updateMode||this.isActive?(0,a.kq)("",!0):((0,a.wg)(),(0,a.iD)("button",{key:1,class:"main-btn px-5 py-3 text-lg flex items-center gap-1",onClick:t[2]||(t[2]=(0,l.iM)((e=>rt.updateEmployeeStatus(!0)),["prevent"]))},[m,(0,a.Uk)(" Активировать ")]))])),this.loading?(0,a.kq)("",!0):((0,a.wg)(),(0,a.iD)("form",{key:2,action:"",onSubmit:t[17]||(t[17]=(0,l.iM)(((...e)=>rt.updateProfileModel&&rt.updateProfileModel(...e)),["prevent"])),class:"shadow-md rounded-xl overflow-hidden border p-5"},[(0,a._)("div",h,[(0,a._)("div",g,[(0,a.Uk)(" Статус: "),this.isActive?((0,a.wg)(),(0,a.iD)("span",w,"Активный")):((0,a.wg)(),(0,a.iD)("span",v,"Деактивированный"))]),(0,a._)("div",f,[(0,a._)("div",y,"Дата создания: "+(0,i.zw)(this.$normaldate(this.dateCreate)),1),(0,a._)("div",b,"Дата обновления: "+(0,i.zw)(this.$normaldate(this.dateUpdate)),1)])]),(0,a._)("div",x,[(0,a._)("div",k,[(0,a._)("div",_,[U,(0,a._)("div",S,(0,i.zw)(e.content.isState?"Штатный":"Совместитель")+" — "+(0,i.zw)(e.faculty.shortName)+" — "+(0,i.zw)(e.department.shortName),1),(0,a._)("div",D,[C,(0,a.Uk)((0,i.zw)(this.username),1)])]),(0,a._)("div",Z,[(0,a._)("div",P,[q,(0,a.wy)((0,a._)("input",{"onUpdate:modelValue":t[3]||(t[3]=t=>e.lastname=t),minlength:"1",required:"",type:"text",name:"",id:"",class:"input-text",placeholder:"Фамилия",title:"Введите фамилию",disabled:!this.updateMode},null,8,A),[[l.nr,e.lastname,void 0,{trim:!0}]])]),(0,a._)("div",I,[L,(0,a.wy)((0,a._)("input",{"onUpdate:modelValue":t[4]||(t[4]=t=>e.name=t),minlength:"1",required:"",type:"text",name:"",id:"",class:"input-text",placeholder:"Имя",title:"Введите имя",disabled:!this.updateMode},null,8,j),[[l.nr,e.name,void 0,{trim:!0}]])]),(0,a._)("div",z,[$,(0,a.wy)((0,a._)("input",{"onUpdate:modelValue":t[5]||(t[5]=t=>e.patronymic=t),minlength:"1",required:"",type:"text",name:"",id:"",class:"input-text",placeholder:"Отчество",title:"Введите отчество",disabled:!this.updateMode},null,8,N),[[l.nr,e.patronymic,void 0,{trim:!0}]])]),(0,a._)("div",T,[E,(0,a.wy)((0,a._)("input",{"onUpdate:modelValue":t[6]||(t[6]=t=>e.birthday=t),type:"date",name:"",id:"",class:"input-text",placeholder:"Дата рождения",title:"Введите дату рождения",disabled:!this.updateMode},null,8,B),[[l.nr,e.birthday,void 0,{trim:!0}]])])])]),(0,a._)("div",V,[(0,a._)("div",O,[K,(0,a.wy)((0,a._)("input",{"onUpdate:modelValue":t[7]||(t[7]=t=>e.academicTitle=t),type:"text",name:"",id:"",class:"input-text",placeholder:"Ученое звание",title:"Введите ученое звание",disabled:!this.updateMode},null,8,F),[[l.nr,e.academicTitle,void 0,{trim:!0}]])]),(0,a._)("div",H,[J,(0,a.wy)((0,a._)("input",{"onUpdate:modelValue":t[8]||(t[8]=t=>e.academicDegree=t),type:"text",name:"",id:"",class:"input-text",placeholder:"Ученая степень",title:"Введите ученую степень",disabled:!this.updateMode},null,8,R),[[l.nr,e.academicDegree,void 0,{trim:!0}]])]),(0,a._)("div",Y,[(0,a._)("div",W,[G,(0,a.wy)((0,a._)("input",{"onUpdate:modelValue":t[9]||(t[9]=t=>e.email=t),type:"email",name:"",id:"",class:"input-text",placeholder:"Email",title:"Введите Email",disabled:!this.updateMode},null,8,Q),[[l.nr,e.email,void 0,{trim:!0}]])]),(0,a._)("div",X,[ee,(0,a.wy)((0,a._)("input",{"onUpdate:modelValue":t[10]||(t[10]=t=>e.phone=t),minlength:"11",maxlength:"11",type:"tel",name:"",id:"",class:"input-text",placeholder:"Телефон",title:"Введите номер телефона без (+)",disabled:!this.updateMode},null,8,te),[[l.nr,e.phone,void 0,{trim:!0}]])])]),(0,a._)("div",se,[(0,a._)("div",ae,[ie,(0,a.wy)((0,a._)("input",{"onUpdate:modelValue":t[11]||(t[11]=t=>e.jobTitle=t),type:"text",name:"",id:"",class:"input-text",placeholder:"Должность",title:"Введите должность",disabled:!this.updateMode},null,8,le),[[l.nr,e.jobTitle,void 0,{trim:!0}]])]),(0,a._)("div",re,[de,(0,a.wy)((0,a._)("input",{"onUpdate:modelValue":t[12]||(t[12]=t=>e.orcid=t),type:"text",name:"",id:"",class:"input-text",placeholder:"ORCID",title:"Введите ORCID",disabled:!this.updateMode},null,8,ne),[[l.nr,e.orcid,void 0,{trim:!0}]])]),(0,a._)("div",oe,[ce,(0,a.wy)((0,a._)("input",{"onUpdate:modelValue":t[13]||(t[13]=t=>e.spinCode=t),type:"text",name:"",id:"",class:"input-text",placeholder:"Spin Code",title:"Введите Spin Code",disabled:!this.updateMode},null,8,pe),[[l.nr,e.spinCode,void 0,{trim:!0}]])])])])]),(0,a._)("div",ue,[me,(0,a._)("div",he,[(0,a._)("div",ge,[we,(0,a._)("div",ve,[(0,a._)("label",fe,[(0,a._)("input",{type:"radio",name:"isState",disabled:!this.updateMode,checked:this.content.isState,id:"1",class:"form-radio-item with-gap block h-5 w-5"},null,8,ye),be]),(0,a._)("label",xe,[(0,a._)("input",{type:"radio",name:"isState",disabled:!this.updateMode,checked:!this.content.isState,id:"0",class:"form-radio-item with-gap block h-5 w-5"},null,8,ke),_e])])]),this.$store.state.auth.user.roles[0]===e.role?((0,a.wg)(),(0,a.iD)("div",Me,[(0,a._)("div",Ue,[Se,((0,a.wg)(),(0,a.j4)(mt,{selected:!1,content:this.departmentListShort,equalId:this.department.shortName,message:"Выберите кафедру",multy:!1,disabled:!this.updateMode,key:this.keyUpdate},null,8,["content","equalId","disabled"]))])])):(0,a.kq)("",!0)]),De]),(0,a._)("div",Ce,[this.updateMode?((0,a.wg)(),(0,a.iD)("div",Ze)):(0,a.kq)("",!0),this.updateMode?(0,a.kq)("",!0):((0,a.wg)(),(0,a.iD)("button",{key:1,class:"main-btn px-5 py-3 text-xl inline-flex items-center gap-1",onClick:t[14]||(t[14]=(0,l.iM)(((...e)=>rt.updatePasswordModal&&rt.updatePasswordModal(...e)),["prevent"]))},[Pe,(0,a.Uk)(" Изменить пароль ")])),this.updateMode?(0,a.kq)("",!0):((0,a.wg)(),(0,a.iD)("button",{key:2,class:"main-btn px-5 py-3 text-xl inline-flex items-center",onClick:t[15]||(t[15]=(0,l.iM)(((...e)=>rt.changeOperationMode&&rt.changeOperationMode(...e)),["prevent"]))},[qe,(0,a.Uk)(" Редактировать ")])),this.updateMode?((0,a.wg)(),(0,a.iD)("div",Ae,[(0,a._)("button",{class:"main-btn-red px-5 py-3 text-xl inline-flex items-center",onClick:t[16]||(t[16]=(0,l.iM)(((...e)=>rt.rajectChangesModal&&rt.rajectChangesModal(...e)),["prevent"]))},[Ie,(0,a.Uk)(" Отменить ")]),Le])):(0,a.kq)("",!0)])],32))]),(0,a._)("div",je,[(0,a._)("div",ze,[$e,Ne,(0,a._)("p",Te,"Вы действительно хотите измененить данные профиля №"+(0,i.zw)(this.userId)+"?",1)]),(0,a._)("div",Ee,[(0,a._)("button",{class:"main-btn inline-block px-5 py-3 w-[150px] text-center text-xl",onClick:t[18]||(t[18]=(...e)=>rt.updateProfile&&rt.updateProfile(...e))},"Да"),Be])]),(0,a._)("div",Ve,[(0,a._)("div",Oe,[Ke,Fe,(0,a._)("p",He,"Вы действительно хотите отменить изменения данных профиля №"+(0,i.zw)(this.userId)+"?",1)]),(0,a._)("div",Je,[(0,a._)("button",{class:"main-btn-yellow inline-block px-5 py-3 w-[150px] text-center text-xl",onClick:t[19]||(t[19]=(...e)=>rt.rajectChanges&&rt.rajectChanges(...e))},"Да"),Re])])]),(0,a._)("div",Ye,[(0,a._)("div",We,[(0,a._)("div",Ge,[Qe,(0,a._)("div",Xe,[(0,a._)("div",et,[(0,a.wy)((0,a._)("input",{"onUpdate:modelValue":t[20]||(t[20]=e=>this.newPassword=e),minlength:"6",maxlength:"100",required:"",type:"password",name:"",id:"",class:"input-text",placeholder:"Введите новый пароль",title:"Введите новый пароль"},null,512),[[l.nr,this.newPassword,void 0,{trim:!0}]])]),this.newPassword.length>0?((0,a.wg)(),(0,a.iD)("div",tt,[this.newPassword.length>5?((0,a.wg)(),(0,a.iD)("div",st,it)):((0,a.wg)(),(0,a.iD)("div",lt,dt))])):(0,a.kq)("",!0)])])]),(0,a._)("div",nt,[ot,(0,a._)("button",{onClick:t[21]||(t[21]=(0,l.iM)(((...e)=>this.updateEmployeePassword&&this.updateEmployeePassword(...e)),["prevent"])),disabled:!(this.newPassword.length>5),class:"main-btn inline-block px-5 py-3 w-[150px] text-center text-xl"},"Обновить",8,ct)])],512)],64)}s(7658);var ut=s(2435),mt=s(8310),ht=s(5753),gt=s(6270),wt=s(573),vt=s(9117),ft=s(8806),yt={name:"DocumentsView",props:["userId"],data:()=>({loading:!0,formSelect:null,content:"",faculty:"",department:"",message:"",updateMode:!1,modalUpdateProfile:null,modalCancelUpdateProfile:null,patronymic:"",name:"",lastname:"",birthday:"",academicTitle:"",academicDegree:"",email:"",phone:"",jobTitle:"",orcid:"",spinCode:"",imgUrl:"",departments:[],departmentsMap:{},username:"",role:ft.v.SuperAdmin,isState:"",departmentId:"",isActive:!1,dateCreate:"",dateUpdate:"",departmentListShort:[],keyUpdate:1,newPassword:"",updateUserPasswordModal:null,canUpdatePassword:!1}),validations(){return{patronymic:{required:vt.C1,minLength:(0,vt.Ei)(1)},name:{required:vt.C1,minLength:(0,vt.Ei)(1)},lastname:{required:vt.C1,minLength:(0,vt.Ei)(1)},birthday:{minLength:(0,vt.Ei)(0)},academicTitle:{minLength:(0,vt.Ei)(0)},academicDegree:{minLength:(0,vt.Ei)(0)},email:{email:vt.Do,minLength:(0,vt.Ei)(0)},phone:{integer:integer,minLength:(0,vt.Ei)(11),maxlength:(0,vt.BS)(11)},jobTitle:{minLength:(0,vt.Ei)(0)},orcid:{integer:integer,minLength:(0,vt.Ei)(0)},spinCode:{integer:integer,minLength:(0,vt.Ei)(0)}}},async mounted(){gt.Z[this.$route.query.message]&&this.$message(gt.Z[this.$route.query.message]),await ht.Z.getEmployeeInfo(this.userId).then((e=>{this.content=e.data,this.lastname=this.content.lastName,this.name=this.content.name,this.patronymic=this.content.patronymic,this.birthday=this.content.birthday,this.academicTitle=this.content.academicTitle,this.academicDegree=this.content.academicDegree,this.email=this.content.email,this.phone=this.content.phone,this.jobTitle=this.content.jobTitle,this.orcid=this.content.orcid,this.spinCode=this.content.spinCode,this.department=this.content.department,this.username=this.content.username,this.isState=this.content.isState,this.departmentId=this.content.department.id,this.isActive=this.content.isActive,this.dateCreate=this.content.dateCreate,this.dateUpdate=this.content.dateUpdate}),(e=>{this.message=e.response&&e.response.data&&e.response.data.message||e.message||e.toString(),this.$error(gt.Z[this.message]||"Что-то пошло не так")})),await wt.Z.getDepartmentsAll().then((e=>{this.departments=e.data,this.departmentListShort=[];for(let t of this.departments)this.departmentListShort.push(t.shortName);for(let t of this.departments)this.departmentsMap[t.shortName]=t.id}),(e=>{this.message=e.response&&e.response.data&&e.response.data.message||e.message||e.toString(),this.$error(gt.Z[this.message]||"Что-то пошло не так")})),await ht.Z.getUserFaculty(this.username).then((e=>{this.faculty=e.data}),(e=>{this.message=e.response&&e.response.data&&e.response.data.message||e.message||e.toString(),this.$error(gt.Z[this.message]||"Что-то пошло не так")})),setTimeout((()=>{this.updateUserPasswordModal=M.Modal.init(this.$refs.updateUserPasswordModal),this.modalUpdateProfile=M.Modal.init(document.querySelector(".modalUpdateProfile"),{}),this.modalCancelUpdateProfile=M.Modal.init(document.querySelector(".modalCancelUpdateProfile"),{}),this.formSelect=M.FormSelect.init(document.querySelectorAll("select"),{})})),this.loading=!1},methods:{changeOperationMode(){this.updateMode=!0,this.updateKeys(),this.$message("Включен режим редактирования")},rajectChanges(){this.modalCancelUpdateProfile.close(),this.updateMode=!1,this.loading=!0,this.updateKeys(),this.$message("Отмена изменений"),setTimeout((()=>{window.location.reload()}),1300)},rajectChangesModal(){this.modalCancelUpdateProfile.open()},updateProfileModel(){""!==this.lastname&&""!==this.name&&""!==this.patronymic?this.modalUpdateProfile.open():this.$error("Заполните все обязательные поля")},async updateProfile(){this.loading=!0,this.modalUpdateProfile.close();let e=this.departmentId;"ROLE_SUPER_ADMIN"===this.$store.state.auth.user.roles[0]&&(e=this.departmentsMap[document.querySelector(".select-wrapper li.selected").innerText]),await ht.Z.updateProfile(this.userId,this.phone,this.email,this.imgUrl,this.lastname,this.name,this.patronymic,this.jobTitle,this.academicTitle,this.academicDegree,this.orcid,this.spinCode,this.birthday,Boolean(Number(document.querySelector("input[type=radio][name=isState]:checked").id)),e),await ht.Z.getEmployeeInfo(this.userId).then((e=>{this.content=e.data,this.lastname=this.content.lastName,this.name=this.content.name,this.patronymic=this.content.patronymic,this.birthday=this.content.birthday,this.academicTitle=this.content.academicTitle,this.academicDegree=this.content.academicDegree,this.email=this.content.email,this.phone=this.content.phone,this.jobTitle=this.content.jobTitle,this.orcid=this.content.orcid,this.spinCode=this.content.spinCode,this.department=this.content.department,this.username=this.content.username,this.isState=this.content.isState,this.departmentId=this.content.department.id,this.isActive=this.content.isActive,this.dateCreate=this.content.dateCreate,this.dateUpdate=this.content.dateUpdate}),(e=>{this.message=e.response&&e.response.data&&e.response.data.message||e.message||e.toString(),this.$error(gt.Z[this.message]||"Что-то пошло не так")})),await ht.Z.getUserFaculty(this.username).then((e=>{this.faculty=e.data}),(e=>{this.message=e.response&&e.response.data&&e.response.data.message||e.message||e.toString(),this.$error(gt.Z[this.message]||"Что-то пошло не так")})),this.updateMode=!1,this.loading=!1,this.updateKeys(),this.$message("Профиль обновлен"),setTimeout((()=>{this.formSelect=M.FormSelect.init(document.querySelectorAll("select"),{})}))},async updateEmployeeStatus(e){await ht.Z.updateUserStatus(this.userId,e).then((e=>{e.data.isActive?this.$message(`Пользователь №${this.userId} активирован`):this.$message(`Пользователь №${this.userId} деактивирован`),this.isActive=e.data.isActive,this.dateUpdate=e.data.dateUpdate}),(e=>{this.message=e.response&&e.response.data&&e.response.data.message||e.message||e.toString(),this.$error(gt.Z[this.message]||"Что-то пошло не так")}))},updatePasswordModal(){this.newPassword="",this.updateUserPasswordModal.open()},async updateEmployeePassword(){await ht.Z.updateUserPassword(this.userId,this.newPassword).then((e=>{navigator.clipboard.writeText(this.newPassword),this.dateUpdate=e.data.dateUpdate,this.$message(`Пароль пользователя №${this.userId} обновелн`),this.$message("Новый пароль сохранен в буфер"),this.updateUserPasswordModal.close()}),(e=>{this.message=e.response&&e.response.data&&e.response.data.message||e.message||e.toString(),this.$error(gt.Z[this.message]||"Что-то пошло не так")}))},updateKeys(){this.keyUpdate+=1},back(){this.$router.go(-1)}},computed:{},beforeDestroy(){this.formSelect&&this.formSelect.destroy&&this.formSelect.destroy(),this.modalUpdateProfile&&this.modalUpdateProfile.destroy&&this.modalUpdateProfile.destroy(),this.modalCancelUpdateProfile&&this.modalCancelUpdateProfile.destroy&&this.modalCancelUpdateProfile.destroy(),this.updateUserPasswordModal&&this.updateUserPasswordModal.destroy&&this.updateUserPasswordModal.destroy()},components:{Sidebar:ut.Z,SelectVue:mt.Z}},bt=s(89);const xt=(0,bt.Z)(yt,[["render",pt]]);var kt=xt}}]);
//# sourceMappingURL=890.23aec3d8.js.map