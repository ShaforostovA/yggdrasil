if(!self.define){let s,l={};const e=(e,i)=>(e=new URL(e+".js",i).href,l[e]||new Promise((l=>{if("document"in self){const s=document.createElement("script");s.src=e,s.onload=l,document.head.appendChild(s)}else s=e,importScripts(e),l()})).then((()=>{let s=l[e];if(!s)throw new Error(`Module ${e} didn’t register its module`);return s})));self.define=(i,r)=>{const n=s||("document"in self?document.currentScript.src:"")||location.href;if(l[n])return;let u={};const j=s=>e(s,n),o={module:{uri:n},exports:u,require:j};l[n]=Promise.all(i.map((s=>o[s]||j(s)))).then((s=>(r(...s),u)))}}define(["./workbox-db5fc017"],(function(s){"use strict";s.setCacheNameDetails({prefix:"yggdrasil-client"}),self.addEventListener("message",(s=>{s.data&&"SKIP_WAITING"===s.data.type&&self.skipWaiting()})),s.precacheAndRoute([{url:"/css/app.d5f6954c.css",revision:null},{url:"/css/style.css",revision:"76e6bf8ae603649ac1da6c09e4bc1271"},{url:"/index.html",revision:"7817ebb14be5af9546cda5f2867b1a5a"},{url:"/js/117.aaead5fe.js",revision:null},{url:"/js/196.a320eb77.js",revision:null},{url:"/js/258.403acb8c.js",revision:null},{url:"/js/292.1c9c68e0.js",revision:null},{url:"/js/307.331978b1.js",revision:null},{url:"/js/311.2829f802.js",revision:null},{url:"/js/315.f1a70749.js",revision:null},{url:"/js/36.5d35c3f9.js",revision:null},{url:"/js/361.090c08a4.js",revision:null},{url:"/js/402.a853e347.js",revision:null},{url:"/js/405.42bc32c0.js",revision:null},{url:"/js/437.3d854de0.js",revision:null},{url:"/js/507.85e5c037.js",revision:null},{url:"/js/53.a518e657.js",revision:null},{url:"/js/543.c00607c3.js",revision:null},{url:"/js/567.41ecff8f.js",revision:null},{url:"/js/575.0b9afb27.js",revision:null},{url:"/js/58.714f9e0b.js",revision:null},{url:"/js/620.372c87e7.js",revision:null},{url:"/js/679.2ebbd7d1.js",revision:null},{url:"/js/704.ee324eff.js",revision:null},{url:"/js/725.1d1f87c9.js",revision:null},{url:"/js/737.aef6382a.js",revision:null},{url:"/js/745.1d097695.js",revision:null},{url:"/js/755.7e7455fe.js",revision:null},{url:"/js/757.b156da4b.js",revision:null},{url:"/js/76.a5ce1b1e.js",revision:null},{url:"/js/806.caf3ed30.js",revision:null},{url:"/js/841.56122cc2.js",revision:null},{url:"/js/852.966d9e34.js",revision:null},{url:"/js/887.df5c5f0f.js",revision:null},{url:"/js/890.48077b2c.js",revision:null},{url:"/js/914.d8c50dee.js",revision:null},{url:"/js/app.00a41d5f.js",revision:null},{url:"/js/chunk-vendors.c71e89cc.js",revision:null},{url:"/manifest.json",revision:"d4dbfa7ea9e5d9ab7aa95366c1b50e08"},{url:"/robots.txt",revision:"b6216d61c03e6ce0c9aea6ca7808f7ca"}],{})}));
//# sourceMappingURL=service-worker.js.map
