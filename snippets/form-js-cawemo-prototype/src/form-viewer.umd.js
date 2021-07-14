(function (global, factory) {
  typeof exports === 'object' && typeof module !== 'undefined' ? factory(exports) :
  typeof define === 'function' && define.amd ? define(['exports'], factory) :
  (global = typeof globalThis !== 'undefined' ? globalThis : global || self, factory(global.FormViewer = {}));
}(this, (function (exports) { 'use strict';

  function mitt(n){return {all:n=n||new Map,on:function(t,e){var i=n.get(t);i&&i.push(e)||n.set(t,[e]);},off:function(t,e){var i=n.get(t);i&&i.splice(i.indexOf(e)>>>0,1);},emit:function(t,e){(n.get(t)||[]).slice().map(function(n){n(e);}),(n.get("*")||[]).slice().map(function(n){n(t,e);});}}}

  /**
   * Flatten array, one level deep.
   *
   * @param {Array<?>} arr
   *
   * @return {Array<?>}
   */

  var nativeToString = Object.prototype.toString;
  var nativeHasOwnProperty = Object.prototype.hasOwnProperty;
  function isUndefined(obj) {
    return obj === undefined;
  }
  function isDefined(obj) {
    return obj !== undefined;
  }
  function isNil(obj) {
    return obj == null;
  }
  function isArray(obj) {
    return nativeToString.call(obj) === '[object Array]';
  }
  /**
   * Return true, if target owns a property with the given key.
   *
   * @param {Object} target
   * @param {String} key
   *
   * @return {Boolean}
   */

  function has(target, key) {
    return nativeHasOwnProperty.call(target, key);
  }
  /**
   * Iterate over collection; returning something
   * (non-undefined) will stop iteration.
   *
   * @param  {Array|Object} collection
   * @param  {Function} iterator
   *
   * @return {Object} return result that stopped the iteration
   */

  function forEach(collection, iterator) {
    var val, result;

    if (isUndefined(collection)) {
      return;
    }

    var convertKey = isArray(collection) ? toNum : identity;

    for (var key in collection) {
      if (has(collection, key)) {
        val = collection[key];
        result = iterator(val, convertKey(key));

        if (result === false) {
          return val;
        }
      }
    }
  }

  function identity(arg) {
    return arg;
  }

  function toNum(arg) {
    return Number(arg);
  }
  /**
   * Sets a nested property of a given object to the specified value.
   *
   * This mutates the object and returns it.
   *
   * @param {Object} target The target of the set operation.
   * @param {(string|number)[]} path The path to the nested value.
   * @param {any} value The value to set.
   */

  function set(target, path, value) {
    var currentTarget = target;
    forEach(path, function (key, idx) {
      if (key === '__proto__') {
        throw new Error('illegal key: __proto__');
      }

      var nextKey = path[idx + 1];
      var nextTarget = currentTarget[key];

      if (isDefined(nextKey) && isNil(nextTarget)) {
        nextTarget = currentTarget[key] = isNaN(+nextKey) ? {} : [];
      }

      if (isUndefined(nextKey)) {
        if (isUndefined(value)) {
          delete currentTarget[key];
        } else {
          currentTarget[key] = value;
        }
      } else {
        currentTarget = nextTarget;
      }
    });
    return target;
  }

  var n,u,i,t,r,o={},f=[],e=/acit|ex(?:s|g|n|p|$)|rph|grid|ows|mnc|ntw|ine[ch]|zoo|^ord|itera/i;function c(n,l){for(var u in l)n[u]=l[u];return n}function s(n){var l=n.parentNode;l&&l.removeChild(n);}function a(n,l,u){var i,t,r,o=arguments,f={};for(r in l)"key"==r?i=l[r]:"ref"==r?t=l[r]:f[r]=l[r];if(arguments.length>3)for(u=[u],r=3;r<arguments.length;r++)u.push(o[r]);if(null!=u&&(f.children=u),"function"==typeof n&&null!=n.defaultProps)for(r in n.defaultProps)void 0===f[r]&&(f[r]=n.defaultProps[r]);return v(n,f,i,t,null)}function v(l,u,i,t,r){var o={type:l,props:u,key:i,ref:t,__k:null,__:null,__b:0,__e:null,__d:void 0,__c:null,__h:null,constructor:void 0,__v:null==r?++n.__v:r};return null!=n.vnode&&n.vnode(o),o}function y(n){return n.children}function p(n,l){this.props=n,this.context=l;}function d(n,l){if(null==l)return n.__?d(n.__,n.__.__k.indexOf(n)+1):null;for(var u;l<n.__k.length;l++)if(null!=(u=n.__k[l])&&null!=u.__e)return u.__e;return "function"==typeof n.type?d(n):null}function _(n){var l,u;if(null!=(n=n.__)&&null!=n.__c){for(n.__e=n.__c.base=null,l=0;l<n.__k.length;l++)if(null!=(u=n.__k[l])&&null!=u.__e){n.__e=n.__c.base=u.__e;break}return _(n)}}function k(l){(!l.__d&&(l.__d=!0)&&u.push(l)&&!m.__r++||t!==n.debounceRendering)&&((t=n.debounceRendering)||i)(m);}function m(){for(var n;m.__r=u.length;)n=u.sort(function(n,l){return n.__v.__b-l.__v.__b}),u=[],n.some(function(n){var l,u,i,t,r,o;n.__d&&(r=(t=(l=n).__v).__e,(o=l.__P)&&(u=[],(i=c({},t)).__v=t.__v+1,T(o,t,i,l.__n,void 0!==o.ownerSVGElement,null!=t.__h?[r]:null,u,null==r?d(t):r,t.__h),j(u,t),t.__e!=r&&_(t)));});}function b(n,l,u,i,t,r,e,c,s,a){var h,p,_,k,m,b,w,A=i&&i.__k||f,P=A.length;for(u.__k=[],h=0;h<l.length;h++)if(null!=(k=u.__k[h]=null==(k=l[h])||"boolean"==typeof k?null:"string"==typeof k||"number"==typeof k?v(null,k,null,null,k):Array.isArray(k)?v(y,{children:k},null,null,null):k.__b>0?v(k.type,k.props,k.key,null,k.__v):k)){if(k.__=u,k.__b=u.__b+1,null===(_=A[h])||_&&k.key==_.key&&k.type===_.type)A[h]=void 0;else for(p=0;p<P;p++){if((_=A[p])&&k.key==_.key&&k.type===_.type){A[p]=void 0;break}_=null;}T(n,k,_=_||o,t,r,e,c,s,a),m=k.__e,(p=k.ref)&&_.ref!=p&&(w||(w=[]),_.ref&&w.push(_.ref,null,k),w.push(p,k.__c||m,k)),null!=m?(null==b&&(b=m),"function"==typeof k.type&&null!=k.__k&&k.__k===_.__k?k.__d=s=g(k,s,n):s=x(n,k,_,A,m,s),a||"option"!==u.type?"function"==typeof u.type&&(u.__d=s):n.value=""):s&&_.__e==s&&s.parentNode!=n&&(s=d(_));}for(u.__e=b,h=P;h--;)null!=A[h]&&("function"==typeof u.type&&null!=A[h].__e&&A[h].__e==u.__d&&(u.__d=d(i,h+1)),L(A[h],A[h]));if(w)for(h=0;h<w.length;h++)I(w[h],w[++h],w[++h]);}function g(n,l,u){var i,t;for(i=0;i<n.__k.length;i++)(t=n.__k[i])&&(t.__=n,l="function"==typeof t.type?g(t,l,u):x(u,t,t,n.__k,t.__e,l));return l}function w(n,l){return l=l||[],null==n||"boolean"==typeof n||(Array.isArray(n)?n.some(function(n){w(n,l);}):l.push(n)),l}function x(n,l,u,i,t,r){var o,f,e;if(void 0!==l.__d)o=l.__d,l.__d=void 0;else if(null==u||t!=r||null==t.parentNode)n:if(null==r||r.parentNode!==n)n.appendChild(t),o=null;else {for(f=r,e=0;(f=f.nextSibling)&&e<i.length;e+=2)if(f==t)break n;n.insertBefore(t,r),o=r;}return void 0!==o?o:t.nextSibling}function A(n,l,u,i,t){var r;for(r in u)"children"===r||"key"===r||r in l||C(n,r,null,u[r],i);for(r in l)t&&"function"!=typeof l[r]||"children"===r||"key"===r||"value"===r||"checked"===r||u[r]===l[r]||C(n,r,l[r],u[r],i);}function P(n,l,u){"-"===l[0]?n.setProperty(l,u):n[l]=null==u?"":"number"!=typeof u||e.test(l)?u:u+"px";}function C(n,l,u,i,t){var r;n:if("style"===l)if("string"==typeof u)n.style.cssText=u;else {if("string"==typeof i&&(n.style.cssText=i=""),i)for(l in i)u&&l in u||P(n.style,l,"");if(u)for(l in u)i&&u[l]===i[l]||P(n.style,l,u[l]);}else if("o"===l[0]&&"n"===l[1])r=l!==(l=l.replace(/Capture$/,"")),l=l.toLowerCase()in n?l.toLowerCase().slice(2):l.slice(2),n.l||(n.l={}),n.l[l+r]=u,u?i||n.addEventListener(l,r?H:$,r):n.removeEventListener(l,r?H:$,r);else if("dangerouslySetInnerHTML"!==l){if(t)l=l.replace(/xlink[H:h]/,"h").replace(/sName$/,"s");else if("href"!==l&&"list"!==l&&"form"!==l&&"download"!==l&&l in n)try{n[l]=null==u?"":u;break n}catch(n){}"function"==typeof u||(null!=u&&(!1!==u||"a"===l[0]&&"r"===l[1])?n.setAttribute(l,u):n.removeAttribute(l));}}function $(l){this.l[l.type+!1](n.event?n.event(l):l);}function H(l){this.l[l.type+!0](n.event?n.event(l):l);}function T(l,u,i,t,r,o,f,e,s){var a,v,h,d,_,k,m,g,w,x,A,P=u.type;if(void 0!==u.constructor)return null;null!=i.__h&&(s=i.__h,e=u.__e=i.__e,u.__h=null,o=[e]),(a=n.__b)&&a(u);try{n:if("function"==typeof P){if(g=u.props,w=(a=P.contextType)&&t[a.__c],x=a?w?w.props.value:a.__:t,i.__c?m=(v=u.__c=i.__c).__=v.__E:("prototype"in P&&P.prototype.render?u.__c=v=new P(g,x):(u.__c=v=new p(g,x),v.constructor=P,v.render=M),w&&w.sub(v),v.props=g,v.state||(v.state={}),v.context=x,v.__n=t,h=v.__d=!0,v.__h=[]),null==v.__s&&(v.__s=v.state),null!=P.getDerivedStateFromProps&&(v.__s==v.state&&(v.__s=c({},v.__s)),c(v.__s,P.getDerivedStateFromProps(g,v.__s))),d=v.props,_=v.state,h)null==P.getDerivedStateFromProps&&null!=v.componentWillMount&&v.componentWillMount(),null!=v.componentDidMount&&v.__h.push(v.componentDidMount);else {if(null==P.getDerivedStateFromProps&&g!==d&&null!=v.componentWillReceiveProps&&v.componentWillReceiveProps(g,x),!v.__e&&null!=v.shouldComponentUpdate&&!1===v.shouldComponentUpdate(g,v.__s,x)||u.__v===i.__v){v.props=g,v.state=v.__s,u.__v!==i.__v&&(v.__d=!1),v.__v=u,u.__e=i.__e,u.__k=i.__k,v.__h.length&&f.push(v);break n}null!=v.componentWillUpdate&&v.componentWillUpdate(g,v.__s,x),null!=v.componentDidUpdate&&v.__h.push(function(){v.componentDidUpdate(d,_,k);});}v.context=x,v.props=g,v.state=v.__s,(a=n.__r)&&a(u),v.__d=!1,v.__v=u,v.__P=l,a=v.render(v.props,v.state,v.context),v.state=v.__s,null!=v.getChildContext&&(t=c(c({},t),v.getChildContext())),h||null==v.getSnapshotBeforeUpdate||(k=v.getSnapshotBeforeUpdate(d,_)),A=null!=a&&a.type===y&&null==a.key?a.props.children:a,b(l,Array.isArray(A)?A:[A],u,i,t,r,o,f,e,s),v.base=u.__e,u.__h=null,v.__h.length&&f.push(v),m&&(v.__E=v.__=null),v.__e=!1;}else null==o&&u.__v===i.__v?(u.__k=i.__k,u.__e=i.__e):u.__e=z(i.__e,u,i,t,r,o,f,s);(a=n.diffed)&&a(u);}catch(l){u.__v=null,(s||null!=o)&&(u.__e=e,u.__h=!!s,o[o.indexOf(e)]=null),n.__e(l,u,i);}}function j(l,u){n.__c&&n.__c(u,l),l.some(function(u){try{l=u.__h,u.__h=[],l.some(function(n){n.call(u);});}catch(l){n.__e(l,u.__v);}});}function z(n,l,u,i,t,r,e,c){var a,v,h,y,p=u.props,d=l.props,_=l.type,k=0;if("svg"===_&&(t=!0),null!=r)for(;k<r.length;k++)if((a=r[k])&&(a===n||(_?a.localName==_:3==a.nodeType))){n=a,r[k]=null;break}if(null==n){if(null===_)return document.createTextNode(d);n=t?document.createElementNS("http://www.w3.org/2000/svg",_):document.createElement(_,d.is&&d),r=null,c=!1;}if(null===_)p===d||c&&n.data===d||(n.data=d);else {if(r=r&&f.slice.call(n.childNodes),v=(p=u.props||o).dangerouslySetInnerHTML,h=d.dangerouslySetInnerHTML,!c){if(null!=r)for(p={},y=0;y<n.attributes.length;y++)p[n.attributes[y].name]=n.attributes[y].value;(h||v)&&(h&&(v&&h.__html==v.__html||h.__html===n.innerHTML)||(n.innerHTML=h&&h.__html||""));}if(A(n,d,p,t,c),h)l.__k=[];else if(k=l.props.children,b(n,Array.isArray(k)?k:[k],l,u,i,t&&"foreignObject"!==_,r,e,n.firstChild,c),null!=r)for(k=r.length;k--;)null!=r[k]&&s(r[k]);c||("value"in d&&void 0!==(k=d.value)&&(k!==n.value||"progress"===_&&!k)&&C(n,"value",k,p.value,!1),"checked"in d&&void 0!==(k=d.checked)&&k!==n.checked&&C(n,"checked",k,p.checked,!1));}return n}function I(l,u,i){try{"function"==typeof l?l(u):l.current=u;}catch(l){n.__e(l,i);}}function L(l,u,i){var t,r,o;if(n.unmount&&n.unmount(l),(t=l.ref)&&(t.current&&t.current!==l.__e||I(t,null,u)),i||"function"==typeof l.type||(i=null!=(r=l.__e)),l.__e=l.__d=void 0,null!=(t=l.__c)){if(t.componentWillUnmount)try{t.componentWillUnmount();}catch(l){n.__e(l,u);}t.base=t.__P=null;}if(t=l.__k)for(o=0;o<t.length;o++)t[o]&&L(t[o],u,i);null!=r&&s(r);}function M(n,l,u){return this.constructor(n,u)}function N(l,u,i){var t,r,e;n.__&&n.__(l,u),r=(t="function"==typeof i)?null:i&&i.__k||u.__k,e=[],T(u,l=(!t&&i||u).__k=a(y,null,[l]),r||o,o,void 0!==u.ownerSVGElement,!t&&i?[i]:r?null:u.firstChild?f.slice.call(u.childNodes):null,e,!t&&i?i:r?r.__e:u.firstChild,t),j(e,l);}function q(n,l){var u={__c:l="__cC"+r++,__:n,Consumer:function(n,l){return n.children(l)},Provider:function(n){var u,i;return this.getChildContext||(u=[],(i={})[l]=this,this.getChildContext=function(){return i},this.shouldComponentUpdate=function(n){this.props.value!==n.value&&u.some(k);},this.sub=function(n){u.push(n);var l=n.componentWillUnmount;n.componentWillUnmount=function(){u.splice(u.indexOf(n),1),l&&l.call(n);};}),n.children}};return u.Provider.__=u.Consumer.contextType=u}n={__e:function(n,l){for(var u,i,t;l=l.__;)if((u=l.__c)&&!u.__)try{if((i=u.constructor)&&null!=i.getDerivedStateFromError&&(u.setState(i.getDerivedStateFromError(n)),t=u.__d),null!=u.componentDidCatch&&(u.componentDidCatch(n),t=u.__d),t)return u.__E=u}catch(l){n=l;}throw n},__v:0},p.prototype.setState=function(n,l){var u;u=null!=this.__s&&this.__s!==this.state?this.__s:this.__s=c({},this.state),"function"==typeof n&&(n=n(c({},u),this.props)),n&&c(u,n),null!=n&&this.__v&&(l&&this.__h.push(l),k(this));},p.prototype.forceUpdate=function(n){this.__v&&(this.__e=!0,n&&this.__h.push(n),k(this));},p.prototype.render=y,u=[],i="function"==typeof Promise?Promise.prototype.then.bind(Promise.resolve()):setTimeout,m.__r=0,r=0;

  var t$1,u$1,r$1,o$1=0,i$1=[],c$1=n.__b,f$1=n.__r,e$1=n.diffed,a$1=n.__c,v$1=n.unmount;function m$1(t,r){n.__h&&n.__h(u$1,t,o$1||r),o$1=0;var i=u$1.__H||(u$1.__H={__:[],__h:[]});return t>=i.__.length&&i.__.push({}),i.__[t]}function l(n){return o$1=1,p$1(w$1,n)}function p$1(n,r,o){var i=m$1(t$1++,2);return i.t=n,i.__c||(i.__=[o?o(r):w$1(void 0,r),function(n){var t=i.t(i.__[0],n);i.__[0]!==t&&(i.__=[t,i.__[1]],i.__c.setState({}));}],i.__c=u$1),i.__}function y$1(r,o){var i=m$1(t$1++,3);!n.__s&&k$1(i.__H,o)&&(i.__=r,i.__H=o,u$1.__H.__h.push(i));}function d$1(n,u){var r=m$1(t$1++,7);return k$1(r.__H,u)&&(r.__=n(),r.__H=u,r.__h=n),r.__}function A$1(n,t){return o$1=8,d$1(function(){return n},t)}function F(n){var r=u$1.context[n.__c],o=m$1(t$1++,9);return o.__c=n,r?(null==o.__&&(o.__=!0,r.sub(u$1)),r.props.value):n.__}function x$1(){i$1.forEach(function(t){if(t.__P)try{t.__H.__h.forEach(g$1),t.__H.__h.forEach(j$1),t.__H.__h=[];}catch(u){t.__H.__h=[],n.__e(u,t.__v);}}),i$1=[];}n.__b=function(n){u$1=null,c$1&&c$1(n);},n.__r=function(n){f$1&&f$1(n),t$1=0;var r=(u$1=n.__c).__H;r&&(r.__h.forEach(g$1),r.__h.forEach(j$1),r.__h=[]);},n.diffed=function(t){e$1&&e$1(t);var o=t.__c;o&&o.__H&&o.__H.__h.length&&(1!==i$1.push(o)&&r$1===n.requestAnimationFrame||((r$1=n.requestAnimationFrame)||function(n){var t,u=function(){clearTimeout(r),b$1&&cancelAnimationFrame(t),setTimeout(n);},r=setTimeout(u,100);b$1&&(t=requestAnimationFrame(u));})(x$1)),u$1=void 0;},n.__c=function(t,u){u.some(function(t){try{t.__h.forEach(g$1),t.__h=t.__h.filter(function(n){return !n.__||j$1(n)});}catch(r){u.some(function(n){n.__h&&(n.__h=[]);}),u=[],n.__e(r,t.__v);}}),a$1&&a$1(t,u);},n.unmount=function(t){v$1&&v$1(t);var u=t.__c;if(u&&u.__H)try{u.__H.__.forEach(g$1);}catch(t){n.__e(t,u.__v);}};var b$1="function"==typeof requestAnimationFrame;function g$1(n){var t=u$1;"function"==typeof n.__c&&n.__c(),u$1=t;}function j$1(n){var t=u$1;n.__c=n.__(),u$1=t;}function k$1(n,t){return !n||n.length!==t.length||t.some(function(t,u){return t!==n[u]})}function w$1(n,t){return "function"==typeof t?t(n):t}

  var e$2={"":["<em>","</em>"],_:["<strong>","</strong>"],"*":["<strong>","</strong>"],"~":["<s>","</s>"],"\n":["<br />"]," ":["<br />"],"-":["<hr />"]};function n$1(e){return e.replace(RegExp("^"+(e.match(/^(\t| )+/)||"")[0],"gm"),"")}function r$2(e){return (e+"").replace(/"/g,"&quot;").replace(/</g,"&lt;").replace(/>/g,"&gt;")}function t$2(a,c){var o,l,g,s,p,u=/((?:^|\n+)(?:\n---+|\* \*(?: \*)+)\n)|(?:^``` *(\w*)\n([\s\S]*?)\n```$)|((?:(?:^|\n+)(?:\t|  {2,}).+)+\n*)|((?:(?:^|\n)([>*+-]|\d+\.)\s+.*)+)|(?:!\[([^\]]*?)\]\(([^)]+?)\))|(\[)|(\](?:\(([^)]+?)\))?)|(?:(?:^|\n+)([^\s].*)\n(-{3,}|={3,})(?:\n+|$))|(?:(?:^|\n+)(#{1,6})\s*(.+)(?:\n+|$))|(?:`([^`].*?)`)|(  \n\n*|\n{2,}|__|\*\*|[_*]|~~)/gm,m=[],h="",i=c||{},d=0;function f(n){var r=e$2[n[1]||""],t=m[m.length-1]==n;return r?r[1]?(t?m.pop():m.push(n),r[0|t]):r[0]:n}function $(){for(var e="";m.length;)e+=f(m[m.length-1]);return e}for(a=a.replace(/^\[(.+?)\]:\s*(.+)$/gm,function(e,n,r){return i[n.toLowerCase()]=r,""}).replace(/^\n+|\n+$/g,"");g=u.exec(a);)l=a.substring(d,g.index),d=u.lastIndex,o=g[0],l.match(/[^\\](\\\\)*\\$/)||((p=g[3]||g[4])?o='<pre class="code '+(g[4]?"poetry":g[2].toLowerCase())+'"><code'+(g[2]?' class="language-'+g[2].toLowerCase()+'"':"")+">"+n$1(r$2(p).replace(/^\n+|\n+$/g,""))+"</code></pre>":(p=g[6])?(p.match(/\./)&&(g[5]=g[5].replace(/^\d+/gm,"")),s=t$2(n$1(g[5].replace(/^\s*[>*+.-]/gm,""))),">"==p?p="blockquote":(p=p.match(/\./)?"ol":"ul",s=s.replace(/^(.*)(\n|$)/gm,"<li>$1</li>")),o="<"+p+">"+s+"</"+p+">"):g[8]?o='<img src="'+r$2(g[8])+'" alt="'+r$2(g[7])+'">':g[10]?(h=h.replace("<a>",'<a href="'+r$2(g[11]||i[l.toLowerCase()])+'">'),o=$()+"</a>"):g[9]?o="<a>":g[12]||g[14]?o="<"+(p="h"+(g[14]?g[14].length:g[13]>"="?1:2))+">"+t$2(g[12]||g[15],i)+"</"+p+">":g[16]?o="<code>"+r$2(g[16])+"</code>":(g[17]||g[1])&&(o=f(g[17]||"--"))),h+=l,h+=o;return (h+a.substring(d)+$()).replace(/^\n+|\n+$/g,"")}

  function o$2(_,o,e,n$1,t){var f={};for(var l in o)"ref"!=l&&(f[l]=o[l]);var s,u,a={type:_,props:f,key:e,ref:o&&o.ref,__k:null,__:null,__b:0,__e:null,__d:void 0,__c:null,__h:null,constructor:void 0,__v:++n.__v,__source:n$1,__self:t};if("function"==typeof _&&(s=_.defaultProps))for(u in s)void 0===f[u]&&(f[u]=s[u]);return n.vnode&&n.vnode(a),a}

  var e$3,o$3={};function n$2(r,t,e){if(3===r.nodeType){var o="textContent"in r?r.textContent:r.nodeValue||"";if(!1!==n$2.options.trim){var a=0===t||t===e.length-1;if((!(o=o.match(/^[\s\n]+$/g)&&"all"!==n$2.options.trim?" ":o.replace(/(^[\s\n]+|[\s\n]+$)/g,"all"===n$2.options.trim||a?"":" "))||" "===o)&&e.length>1&&a)return null}return o}if(1!==r.nodeType)return null;var p=String(r.nodeName).toLowerCase();if("script"===p&&!n$2.options.allowScripts)return null;var l,s,u=n$2.h(p,function(r){var t=r&&r.length;if(!t)return null;for(var e={},o=0;o<t;o++){var a=r[o],i=a.name,p=a.value;"on"===i.substring(0,2)&&n$2.options.allowEvents&&(p=new Function(p)),e[i]=p;}return e}(r.attributes),(s=(l=r.childNodes)&&Array.prototype.map.call(l,n$2).filter(i$2))&&s.length?s:null);return n$2.visitor&&n$2.visitor(u),u}var a$2,i$2=function(r){return r},p$2={};function l$1(r){var t=(r.type||"").toLowerCase(),e=l$1.map;e&&e.hasOwnProperty(t)?(r.type=e[t],r.props=Object.keys(r.props||{}).reduce(function(t,e){var o;return t[(o=e,o.replace(/-(.)/g,function(r,t){return t.toUpperCase()}))]=r.props[e],t},{})):r.type=t.replace(/[^a-z0-9-]/i,"");}var Markup = (function(t){function i(){t.apply(this,arguments);}return t&&(i.__proto__=t),(i.prototype=Object.create(t&&t.prototype)).constructor=i,i.setReviver=function(r){a$2=r;},i.prototype.shouldComponentUpdate=function(r){var t=this.props;return r.wrap!==t.wrap||r.type!==t.type||r.markup!==t.markup},i.prototype.setComponents=function(r){if(this.map={},r)for(var t in r)if(r.hasOwnProperty(t)){var e=t.replace(/([A-Z]+)([A-Z][a-z0-9])|([a-z0-9]+)([A-Z])/g,"$1$3-$2$4").toLowerCase();this.map[e]=r[t];}},i.prototype.render=function(t){var i=t.wrap;void 0===i&&(i=!0);var s,u=t.type,c=t.markup,m=t.components,v=t.reviver,f=t.onError,d=t["allow-scripts"],h=t["allow-events"],y=t.trim,w=function(r,t){var e={};for(var o in r)Object.prototype.hasOwnProperty.call(r,o)&&-1===t.indexOf(o)&&(e[o]=r[o]);return e}(t,["wrap","type","markup","components","reviver","onError","allow-scripts","allow-events","trim"]),C=v||this.reviver||this.constructor.prototype.reviver||a$2||a;this.setComponents(m);var g={allowScripts:d,allowEvents:h,trim:y};try{s=function(r,t,a,i,s){var u=function(r,t){var o,n,a,i,p="html"===t?"text/html":"application/xml";"html"===t?(i="body",a="<!DOCTYPE html>\n<html><body>"+r+"</body></html>"):(i="xml",a='<?xml version="1.0" encoding="UTF-8"?>\n<xml>'+r+"</xml>");try{o=(new DOMParser).parseFromString(a,p);}catch(r){n=r;}if(o||"html"!==t||((o=e$3||(e$3=function(){if(document.implementation&&document.implementation.createHTMLDocument)return document.implementation.createHTMLDocument("");var r=document.createElement("iframe");return r.style.cssText="position:absolute; left:0; top:-999em; width:1px; height:1px; overflow:hidden;",r.setAttribute("sandbox","allow-forms"),document.body.appendChild(r),r.contentWindow.document}())).open(),o.write(a),o.close()),o){var l=o.getElementsByTagName(i)[0],s=l.firstChild;return r&&!s&&(l.error="Document parse failed."),s&&"parsererror"===String(s.nodeName).toLowerCase()&&(s.removeChild(s.firstChild),s.removeChild(s.lastChild),l.error=s.textContent||s.nodeValue||n||"Unknown error",l.removeChild(s)),l}}(r,t);if(u&&u.error)throw new Error(u.error);var c=u&&u.body||u;l$1.map=i||p$2;var m=c&&function(r,t,e,a){return n$2.visitor=t,n$2.h=e,n$2.options=a||o$3,n$2(r)}(c,l$1,a,s);return l$1.map=null,m&&m.props&&m.props.children||null}(c,u,C,this.map,g);}catch(r){f?f({error:r}):"undefined"!=typeof console&&console.error&&console.error("preact-markup: "+r);}if(!1===i)return s||null;var x=w.hasOwnProperty("className")?"className":"class",b=w[x];return b?b.splice?b.splice(0,0,"markup"):"string"==typeof b?w[x]+=" markup":"object"==typeof b&&(b.markup=!0):w[x]="markup",C("div",w,s||null)},i}(p));

  function C$1(n,t){for(var e in t)n[e]=t[e];return n}function S(n,t){for(var e in n)if("__source"!==e&&!(e in t))return !0;for(var r in t)if("__source"!==r&&n[r]!==t[r])return !0;return !1}function E(n){this.props=n;}(E.prototype=new p).isPureReactComponent=!0,E.prototype.shouldComponentUpdate=function(n,t){return S(this.props,n)||S(this.state,t)};var w$2=n.__b;n.__b=function(n){n.type&&n.type.__f&&n.ref&&(n.props.ref=n.ref,n.ref=null),w$2&&w$2(n);};var A$2=n.__e;function O(){this.__u=0,this.t=null,this.__b=null;}function L$1(n){var t=n.__.__c;return t&&t.__e&&t.__e(n)}function D(){this.u=null,this.o=null;}n.__e=function(n,t,e){if(n.then)for(var r,u=t;u=u.__;)if((r=u.__c)&&r.__c)return null==t.__e&&(t.__e=e.__e,t.__k=e.__k),r.__c(n,t);A$2(n,t,e);},(O.prototype=new p).__c=function(n,t){var e=t.__c,r=this;null==r.t&&(r.t=[]),r.t.push(e);var u=L$1(r.__v),o=!1,i=function(){o||(o=!0,e.componentWillUnmount=e.__c,u?u(l):l());};e.__c=e.componentWillUnmount,e.componentWillUnmount=function(){i(),e.__c&&e.__c();};var l=function(){if(!--r.__u){if(r.state.__e){var n=r.state.__e;r.__v.__k[0]=function n(t,e,r){return t&&(t.__v=null,t.__k=t.__k&&t.__k.map(function(t){return n(t,e,r)}),t.__c&&t.__c.__P===e&&(t.__e&&r.insertBefore(t.__e,t.__d),t.__c.__e=!0,t.__c.__P=r)),t}(n,n.__c.__P,n.__c.__O);}var t;for(r.setState({__e:r.__b=null});t=r.t.pop();)t.forceUpdate();}},f=!0===t.__h;r.__u++||f||r.setState({__e:r.__b=r.__v.__k[0]}),n.then(i,i);},O.prototype.componentWillUnmount=function(){this.t=[];},O.prototype.render=function(n,t){if(this.__b){if(this.__v.__k){var e=document.createElement("div"),r=this.__v.__k[0].__c;this.__v.__k[0]=function n(t,e,r){return t&&(t.__c&&t.__c.__H&&(t.__c.__H.__.forEach(function(n){"function"==typeof n.__c&&n.__c();}),t.__c.__H=null),null!=(t=C$1({},t)).__c&&(t.__c.__P===r&&(t.__c.__P=e),t.__c=null),t.__k=t.__k&&t.__k.map(function(t){return n(t,e,r)})),t}(this.__b,e,r.__O=r.__P);}this.__b=null;}var u=t.__e&&a(y,null,n.fallback);return u&&(u.__h=null),[a(y,null,t.__e?null:n.children),u]};var F$1=function(n,t,e){if(++e[1]===e[0]&&n.o.delete(t),n.props.revealOrder&&("t"!==n.props.revealOrder[0]||!n.o.size))for(e=n.u;e;){for(;e.length>3;)e.pop()();if(e[1]<e[0])break;n.u=e=e[2];}};function M$1(n){return this.getChildContext=function(){return n.context},n.children}function T$1(n){var t=this,e=n.i;t.componentWillUnmount=function(){N(null,t.l),t.l=null,t.i=null;},t.i&&t.i!==e&&t.componentWillUnmount(),n.__v?(t.l||(t.i=e,t.l={nodeType:1,parentNode:e,childNodes:[],appendChild:function(n){this.childNodes.push(n),t.i.appendChild(n);},insertBefore:function(n,e){this.childNodes.push(n),t.i.appendChild(n);},removeChild:function(n){this.childNodes.splice(this.childNodes.indexOf(n)>>>1,1),t.i.removeChild(n);}}),N(a(M$1,{context:t.context},n.__v),t.l)):t.l&&t.componentWillUnmount();}function j$2(n,t){return a(T$1,{__v:n,i:t})}(D.prototype=new p).__e=function(n){var t=this,e=L$1(t.__v),r=t.o.get(n);return r[0]++,function(u){var o=function(){t.props.revealOrder?(r.push(u),F$1(t,n,r)):u();};e?e(o):o();}},D.prototype.render=function(n){this.u=null,this.o=new Map;var t=w(n.children);n.revealOrder&&"b"===n.revealOrder[0]&&t.reverse();for(var e=t.length;e--;)this.o.set(t[e],this.u=[1,0,this.u]);return n.children},D.prototype.componentDidUpdate=D.prototype.componentDidMount=function(){var n=this;this.o.forEach(function(t,e){F$1(n,e,t);});};var I$1="undefined"!=typeof Symbol&&Symbol.for&&Symbol.for("react.element")||60103,W=/^(?:accent|alignment|arabic|baseline|cap|clip(?!PathU)|color|fill|flood|font|glyph(?!R)|horiz|marker(?!H|W|U)|overline|paint|stop|strikethrough|stroke|text(?!L)|underline|unicode|units|v|vector|vert|word|writing|x(?!C))[A-Z]/,P$1=function(n){return ("undefined"!=typeof Symbol&&"symbol"==typeof Symbol()?/fil|che|rad/i:/fil|che|ra/i).test(n)};p.prototype.isReactComponent={},["componentWillMount","componentWillReceiveProps","componentWillUpdate"].forEach(function(n){Object.defineProperty(p.prototype,n,{configurable:!0,get:function(){return this["UNSAFE_"+n]},set:function(t){Object.defineProperty(this,n,{configurable:!0,writable:!0,value:t});}});});var B=n.event;function H$1(){}function Z(){return this.cancelBubble}function Y(){return this.defaultPrevented}n.event=function(n){return B&&(n=B(n)),n.persist=H$1,n.isPropagationStopped=Z,n.isDefaultPrevented=Y,n.nativeEvent=n};var q$1={configurable:!0,get:function(){return this.class}},G=n.vnode;n.vnode=function(n){var t=n.type,e=n.props,r=e;if("string"==typeof t){for(var u in r={},e){var o=e[u];"value"===u&&"defaultValue"in e&&null==o||("defaultValue"===u&&"value"in e&&null==e.value?u="value":"download"===u&&!0===o?o="":/ondoubleclick/i.test(u)?u="ondblclick":/^onchange(textarea|input)/i.test(u+t)&&!P$1(e.type)?u="oninput":/^on(Ani|Tra|Tou|BeforeInp)/.test(u)?u=u.toLowerCase():W.test(u)?u=u.replace(/[A-Z0-9]/,"-$&").toLowerCase():null===o&&(o=void 0),r[u]=o);}"select"==t&&r.multiple&&Array.isArray(r.value)&&(r.value=w(e.children).forEach(function(n){n.props.selected=-1!=r.value.indexOf(n.props.value);})),"select"==t&&null!=r.defaultValue&&(r.value=w(e.children).forEach(function(n){n.props.selected=r.multiple?-1!=r.defaultValue.indexOf(n.props.value):r.defaultValue==n.props.value;})),n.props=r;}t&&e.class!=e.className&&(q$1.enumerable="className"in e,null!=e.className&&(r.class=e.className),Object.defineProperty(r,"className",q$1)),n.$$typeof=I$1,G&&G(n);};var J=n.__r;n.__r=function(n){J&&J(n),n.__c;};"object"==typeof performance&&"function"==typeof performance.now?performance.now.bind(performance):function(){return Date.now()};

  class Validator {
    validateField(field, value) {
      const {
        validate
      } = field;
      let errors = [];

      if (!validate) {
        return errors;
      }

      if (validate.pattern && value && !new RegExp(validate.pattern).test(value)) {
        errors = [...errors, `Field must match pattern ${validate.pattern}.`];
      }

      if (validate.required && typeof value === 'undefined') {
        errors = [...errors, 'Field is required.'];
      }

      if ('min' in validate && value && value < validate.min) {
        errors = [...errors, `Field must have minimum value of ${validate.min}.`];
      }

      if ('max' in validate && value && value > validate.max) {
        errors = [...errors, `Field must have maximum value of ${validate.max}.`];
      }

      if ('minLength' in validate && value && value.trim().length < validate.minLength) {
        errors = [...errors, `Field must have minimum length of ${validate.minLength}.`];
      }

      if ('maxLength' in validate && value && value.trim().length > validate.maxLength) {
        errors = [...errors, `Field must have maximum length of ${validate.maxLength}.`];
      }

      return errors;
    }

  }

  function findData(data, path) {
    for (const key of path) {
      data = data[key];

      if (data === undefined) {
        return undefined;
      }
    }

    return data;
  }
  function findErrors(errors, path) {
    return errors[pathStringify(path)];
  }
  function findFieldRenderer(renderers, type) {
    return renderers.find(renderer => {
      return renderer.type === type;
    });
  }
  function pathsEqual(a, b) {
    return a && b && a.length === b.length && a.every((value, index) => value === b[index]);
  }
  function pathStringify(path) {
    if (!path) {
      return '';
    }

    return path.join('.');
  }
  const indices = {};

  const generateIndexForType = type => {
    if (type in indices) {
      indices[type]++;
    } else {
      indices[type] = 1;
    }

    return indices[type];
  };

  const generateIdForType = type => {
    return `${type}${generateIndexForType(type)}`;
  };
  function clone(data, replacer) {
    return JSON.parse(JSON.stringify(data, replacer));
  }
  function importSchema(schema) {
    schema = clone(schema);
    const fields = new Map();
    importField(schema, fields);
    return {
      schema,
      fields
    };
  }

  function importField(field, fields, parent, index) {
    const id = generateIdForType(field.type);
    field.id = id;
    fields.set(id, field);

    if (parent) {
      field.parent = parent.id;
      field.path = parent.path ? [...parent.path, 'components', index] : ['components', index];
    } else {
      field.path = [];
    }

    if (field.components) {
      importFields(field.components, fields, field);
    }
  }

  function importFields(components, fields, parent) {
    components.forEach((component, index) => {
      importField(component, fields, parent, index);
    });
  }

  /**
   * @typedef { any } Schema
   * @typedef { { [x: string]: any } } Data
   * @typedef { { [x: string]: string[] } } Errors
   * @typedef { ('readOnly') } Properties
   * @typedef { { readOnly?: boolean } } PropertyOptions
   * @typedef { ('submit' | 'changed') } Events
   * @typedef { { data: Data, schema: Schema, properties?: PropertyOptions } } FormCoreOptions
   * @typedef { { data: Data, errors: Errors, schema: Schema, properties: PropertyOptions } } State
   */

  /**
   * The form core.
   */

  class FormCore {
    /**
     * @constructor
     * @param { FormCoreOptions } options
     */
    constructor(options) {
      const {
        schema = {},
        data = {},
        properties = {}
      } = options;
      /**
       * @private
       */

      this.emitter = mitt();
      this.validator = new Validator();
      this.fields = new Map();
      const {
        schema: importedSchema
      } = importSchema(schema);
      /**
       * @private
       * @type {Schema}
       */

      this.initialSchema = clone(importedSchema);
      /**
       * @private
       * @type {Data}
       */

      this.initialData = clone(data);
      /**
       * @type {State}
       */

      this.state = {
        data: clone(this.initialData),
        errors: {},
        schema: clone(this.initialSchema),
        properties
      };
    }

    reset() {
      this.emitter.emit('reset');
      this.setState({
        data: clone(this.initialData),
        schema: clone(this.initialSchema),
        errors: {}
      });
    }
    /**
     * @returns { { data: Data, errors: Errors } }
     */


    submit() {
      const data = this.state.data;
      const errors = this.validateAll(data);
      const payload = clone({
        data,
        errors
      });
      this.emitter.emit('submit', payload);
      return payload;
    }
    /**
     * @param  { { path: (string|number)[], value: any } } update
     */


    update(update) {
      const {
        path,
        value
      } = update;
      const field = Array.from(this.fields.values()).find(field => pathsEqual(field.path, path));
      const fieldErrors = this.validator.validateField(field, value);
      const data = set(this.getState().data, path, value);
      const errors = set(this.getState().errors, path, fieldErrors.length ? fieldErrors : undefined);
      this.setState({
        data,
        errors
      });
    }
    /**
     * @param { Data } data
     * @returns Errors
     */


    validateAll(data) {
      const errors = Array.from(this.fields.values()).reduce((errors, field) => {
        const {
          path
        } = field;
        const value = findData(data, path);
        const fieldErrors = this.validator.validateField(field, value);
        return set(errors, path, fieldErrors.length ? fieldErrors : undefined);
      }, {});
      this.setState({
        errors
      });
      return this.getState().errors;
    }

    getState() {
      return clone(this.state);
    }
    /**
     * @param { Partial<State> } state
     */


    setState(state) {
      this.state = { ...this.state,
        ...state
      };
      this.changed(this.state);
    }
    /**
     * @param { State } state
     */


    changed(state) {
      this.emitter.emit('changed', clone(state));
    }
    /**
     * @param { Properties } property
     * @param { any } value
     */


    setProperty(property, value) {
      const properties = set(this.getState().properties, [property], value);
      this.setState({
        properties
      });
    }
    /**
     * @type { (event: Events, callback: (state: any) => void) => void }
     */


    on(event, callback) {
      this.emitter.on(event, callback);
    }
    /**
     * @type { (event: Events, callback: (state: any) => void) => void }
     */


    off(event, callback) {
      this.emitter.off(event, callback);
    }

  }

  const FormRenderContext = q({
    Empty: props => {
      return null;
    },
    Children: props => {
      return props.children;
    },
    Element: props => {
      return props.children;
    }
  });

  const FormContext = q({
    fields: new Map(),
    data: {},
    errors: {},
    properties: {},

    getFieldRenderer(type) {
      return null;
    }

  });

  const noop = () => false;

  function FormElement(props) {
    const {
      path,
      field,
      onChange
    } = props;
    const {
      data,
      errors,
      fields,
      properties,
      getFieldRenderer
    } = F(FormContext);
    const {
      Element
    } = F(FormRenderContext);
    const Renderer = getFieldRenderer(field.type);
    const {
      id
    } = field;

    if (!Renderer) {
      throw new Error(`cannot render field <${field.type}>`);
    }

    const value = findData(data, path);
    const fieldErrors = findErrors(errors, path);
    y$1(() => {
      fields.set(id, { ...field,
        path
      });
    }, [pathStringify(path)]);
    return o$2(Element, {
      field: field,
      children: o$2(Renderer, { ...props,
        disabled: properties.readOnly || false,
        errors: fieldErrors,
        onChange: properties.readOnly ? noop : onChange,
        value: value
      })
    });
  }

  /**
   * This file must not be changed or exchanged.
   *
   * @see http://bpmn.io/license for more information.
   */

  function Logo() {
    return o$2("svg", {
      xmlns: "http://www.w3.org/2000/svg",
      viewBox: "0 0 14.02 5.57",
      width: "53",
      height: "21",
      style: "vertical-align:middle",
      children: [o$2("path", {
        fill: "currentColor",
        d: "M1.88.92v.14c0 .41-.13.68-.4.8.33.14.46.44.46.86v.33c0 .61-.33.95-.95.95H0V0h.95c.65 0 .93.3.93.92zM.63.57v1.06h.24c.24 0 .38-.1.38-.43V.98c0-.28-.1-.4-.32-.4zm0 1.63v1.22h.36c.2 0 .32-.1.32-.39v-.35c0-.37-.12-.48-.4-.48H.63zM4.18.99v.52c0 .64-.31.98-.94.98h-.3V4h-.62V0h.92c.63 0 .94.35.94.99zM2.94.57v1.35h.3c.2 0 .3-.09.3-.37v-.6c0-.29-.1-.38-.3-.38h-.3zm2.89 2.27L6.25 0h.88v4h-.6V1.12L6.1 3.99h-.6l-.46-2.82v2.82h-.55V0h.87zM8.14 1.1V4h-.56V0h.79L9 2.4V0h.56v4h-.64zm2.49 2.29v.6h-.6v-.6zM12.12 1c0-.63.33-1 .95-1 .61 0 .95.37.95 1v2.04c0 .64-.34 1-.95 1-.62 0-.95-.37-.95-1zm.62 2.08c0 .28.13.39.33.39s.32-.1.32-.4V.98c0-.29-.12-.4-.32-.4s-.33.11-.33.4z"
      }), o$2("path", {
        fill: "currentColor",
        d: "M0 4.53h14.02v1.04H0zM11.08 0h.63v.62h-.63zm.63 4V1h-.63v2.98z"
      })]
    });
  }

  function Lightbox(props) {
    const {
      open
    } = props;

    if (!open) {
      return null;
    }

    return o$2("div", {
      class: "fjs-powered-by-lightbox",
      style: "z-index: 100; position: fixed; top: 0; left: 0;right: 0; bottom: 0",
      children: [o$2("div", {
        class: "backdrop",
        style: "width: 100%; height: 100%; background: rgba(40 40 40 / 20%)",
        onClick: props.onBackdropClick
      }), o$2("div", {
        class: "notice",
        style: "position: absolute; left: 50%; top: 40%; transform: translate(-50%); width: 260px; padding: 10px; background: white; box-shadow: 0  1px 4px rgba(0 0 0 / 30%); font-family: Helvetica, Arial, sans-serif; font-size: 14px; display: flex; line-height: 1.3",
        children: [o$2("a", {
          href: "https://bpmn.io",
          target: "_blank",
          rel: "noopener",
          style: "margin: 15px 20px 15px 10px; align-self: center; color: #404040",
          children: o$2(Logo, {})
        }), o$2("span", {
          children: ["Web-based tooling for BPMN, DMN, and forms powered by ", o$2("a", {
            href: "https://bpmn.io",
            target: "_blank",
            rel: "noopener",
            children: "bpmn.io"
          }), "."]
        })]
      })]
    });
  }

  function Link(props) {
    return o$2("div", {
      class: "fjs-powered-by fjs-form-field",
      style: "text-align: right",
      children: o$2("a", {
        href: "https://bpmn.io",
        target: "_blank",
        rel: "noopener",
        class: "fjs-powered-by-link",
        title: "Powered by bpmn.io",
        style: "color: #404040",
        onClick: props.onClick,
        children: o$2(Logo, {})
      })
    });
  }

  function PoweredBy(props) {
    const [open, setOpen] = l(false);

    function toggleOpen(open) {
      return event => {
        event.preventDefault();
        setOpen(open);
      };
    }

    return o$2(y, {
      children: [j$2(o$2(Lightbox, {
        open: open,
        onBackdropClick: toggleOpen(false)
      }), document.body), o$2(Link, {
        onClick: toggleOpen(true)
      })]
    });
  }

  const noop$1 = () => {};

  function Form(props) {
    const {
      onSubmit = noop$1,
      onReset = noop$1,
      onChange = noop$1,
      schema
    } = props;

    const handleSubmit = event => {
      event.preventDefault();
      onSubmit();
    };

    const handleReset = event => {
      event.preventDefault();
      onReset();
    };

    return o$2("form", {
      class: "fjs-form",
      onSubmit: handleSubmit,
      onReset: handleReset,
      children: [o$2(FormElement, {
        field: schema,
        onChange: onChange,
        path: []
      }), o$2(PoweredBy, {})]
    });
  }

  function formFieldClasses(type, errors = []) {
    if (!type) {
      throw new Error('type required');
    }

    const classes = ['fjs-form-field', `fjs-form-field-${type}`];

    if (errors.length) {
      classes.push('fjs-has-errors');
    }

    return classes.join(' ');
  }
  function prefixId(id) {
    return `fjs-form-${id}`;
  }
  const NODE_TYPE_TEXT = 3,
        NODE_TYPE_ELEMENT = 1;
  const DISALLOWED_NODES = ['embed', 'iframe', 'object', 'script', 'svg'];
  const ALLOWED_ATTRIBUTES = ['align', 'alt', 'class', 'href', 'id', 'name', 'src', 'valign']; // See https://github.com/developit/snarkdown/issues/70

  function safeMarkdown(markdown) {
    const html = t$2(markdown);
    const doc = new DOMParser().parseFromString(`<!DOCTYPE html>\n<html><body><div>${html}`, 'text/html');
    doc.normalize();
    sanitize(doc.body);
    return new XMLSerializer().serializeToString(doc.body.firstChild);
  }

  function sanitize(node) {
    if (node.nodeType === NODE_TYPE_TEXT) {
      return;
    }

    if (node.nodeType !== NODE_TYPE_ELEMENT || DISALLOWED_NODES.includes(node.tagName.toLowerCase())) {
      return node.remove();
    }

    for (let i = node.attributes.length; i--;) {
      const name = node.attributes[i].name;

      if (!ALLOWED_ATTRIBUTES.includes(name.toLowerCase())) {
        node.attributes.removeNamedItem(name);
      }

      if (name === 'href') {
        const href = node.attributes.getNamedItem('href');

        if (href.value.includes('javascript:')) {
          node.attributes.removeNamedItem('href');
        }
      }
    }

    for (let i = node.childNodes.length; i--;) {
      sanitize(node.childNodes[i]);
    }
  }

  const type = 'button';
  function ButtonRenderer(props) {
    const {
      disabled,
      field
    } = props;
    const {
      action = 'submit'
    } = field;
    return o$2("div", {
      class: formFieldClasses(type),
      children: o$2("button", {
        class: "fjs-button",
        type: action,
        disabled: disabled,
        children: field.label
      })
    });
  }

  ButtonRenderer.create = function (options = {}) {
    const id = generateIdForType(type);
    return {
      action: 'submit',
      id,
      key: id,
      label: this.label,
      type,
      ...options
    };
  };

  ButtonRenderer.type = type;
  ButtonRenderer.label = 'Button';

  function Description(props) {
    const {
      description
    } = props;

    if (!description) {
      return null;
    }

    return o$2("div", {
      class: "fjs-form-field-description",
      children: description
    });
  }

  function Errors(props) {
    const {
      errors
    } = props;

    if (!errors.length) {
      return null;
    }

    return o$2("div", {
      class: "fjs-form-field-error",
      children: o$2("ul", {
        children: errors.map(error => {
          return o$2("li", {
            children: error
          });
        })
      })
    });
  }

  function Label(props) {
    const {
      id,
      label,
      required = false
    } = props;

    if (!label) {
      return null;
    }

    return o$2("label", {
      for: id,
      class: "fjs-form-field-label",
      children: [props.children, label, required && o$2("span", {
        class: "fjs-asterix",
        children: "*"
      })]
    });
  }

  const type$1 = 'checkbox';
  function CheckboxRenderer(props) {
    const {
      disabled,
      errors = [],
      field,
      path,
      value = false
    } = props;
    const {
      description,
      id,
      label
    } = field;

    const onChange = ({
      target
    }) => {
      props.onChange({
        path,
        value: target.checked
      });
    };

    return o$2("div", {
      class: formFieldClasses(type$1, errors),
      children: [o$2(Label, {
        id: prefixId(id),
        label: label,
        required: false,
        children: o$2("input", {
          checked: value,
          class: "fjs-input",
          disabled: disabled,
          id: prefixId(id),
          type: "checkbox",
          onChange: onChange
        })
      }), o$2(Description, {
        description: description
      }), o$2(Errors, {
        errors: errors
      })]
    });
  }

  CheckboxRenderer.create = function (options = {}) {
    const id = generateIdForType(type$1);
    return {
      id,
      key: id,
      label: this.label,
      type: type$1,
      ...options
    };
  };

  CheckboxRenderer.type = type$1;
  CheckboxRenderer.label = 'Checkbox';

  function DefaultRenderer(props) {
    const {
      Children,
      Empty
    } = F(FormRenderContext);
    let {
      field,
      path
    } = props;
    const {
      id
    } = field;
    const {
      components = []
    } = field;
    return o$2(Children, {
      class: "fjs-vertical-layout",
      field: field,
      children: [components.map(field => {
        if (field.key) {
          path = [...path.slice(0, -1), field.key];
        } else {
          path = path.slice(0, -1);
        }

        return a(FormElement, { ...props,
          key: id,
          path: path,
          field: field
        });
      }), components.length ? null : o$2(Empty, {})]
    });
  }

  DefaultRenderer.create = function (options = {}) {
    const id = generateIdForType(this.type);
    return {
      components: [],
      id,
      label: this.label,
      type: this.type,
      ...options
    };
  };

  DefaultRenderer.type = 'default';
  DefaultRenderer.label = 'Default';

  const type$2 = 'number';
  function NumberRenderer(props) {
    const {
      disabled,
      errors = [],
      field,
      path,
      value
    } = props;
    const {
      description,
      id,
      label,
      validate = {}
    } = field;
    const {
      required
    } = validate;

    const onChange = ({
      target
    }) => {
      props.onChange({
        path,
        value: target.value ? parseInt(target.value, 10) : undefined
      });
    };

    return o$2("div", {
      class: formFieldClasses(type$2, errors),
      children: [o$2(Label, {
        id: prefixId(id),
        label: label,
        required: required
      }), o$2("input", {
        class: "fjs-input",
        disabled: disabled,
        id: prefixId(id),
        onInput: onChange,
        type: "number",
        value: value
      }), o$2(Description, {
        description: description
      }), o$2(Errors, {
        errors: errors
      })]
    });
  }

  NumberRenderer.create = function (options = {}) {
    const id = generateIdForType(type$2);
    return {
      id,
      key: id,
      label: this.label,
      type: type$2,
      ...options
    };
  };

  NumberRenderer.type = type$2;
  NumberRenderer.label = 'Number';

  const type$3 = 'radio';
  function RadioRenderer(props) {
    const {
      disabled,
      errors = [],
      field,
      path,
      value
    } = props;
    const {
      description,
      id,
      label,
      validate = {},
      values
    } = field;
    const {
      required
    } = validate;

    const onChange = v => {
      props.onChange({
        path,
        value: v === value ? undefined : v
      });
    };

    return o$2("div", {
      class: formFieldClasses(type$3, errors),
      children: [o$2(Label, {
        label: label,
        required: required
      }), values.map((v, index) => {
        return o$2(Label, {
          id: prefixId(`${id}-${index}`),
          label: v.label,
          required: false,
          children: o$2("input", {
            checked: v.value === value,
            class: "fjs-input",
            disabled: disabled,
            id: prefixId(`${id}-${index}`),
            type: "radio",
            onClick: () => onChange(v.value)
          })
        });
      }), o$2(Description, {
        description: description
      }), o$2(Errors, {
        errors: errors
      })]
    });
  }

  RadioRenderer.create = function (options = {}) {
    const id = generateIdForType(type$3);
    return {
      id,
      key: id,
      label: this.label,
      type: type$3,
      values: [{
        label: 'Value',
        value: 'value'
      }],
      ...options
    };
  };

  RadioRenderer.type = type$3;
  RadioRenderer.label = 'Radio';

  const type$4 = 'select';
  function SelectRenderer(props) {
    const {
      disabled,
      errors = [],
      field,
      path,
      value
    } = props;
    const {
      description,
      id,
      label,
      validate = {},
      values
    } = field;
    const {
      required
    } = validate;

    const onChange = ({
      target
    }) => {
      props.onChange({
        path,
        value: target.value === '' ? undefined : target.value
      });
    };

    return o$2("div", {
      class: formFieldClasses(type$4, errors),
      children: [o$2(Label, {
        label: label,
        required: required
      }), o$2("select", {
        class: "fjs-select",
        disabled: disabled,
        id: prefixId(id),
        onChange: onChange,
        value: value,
        children: [o$2("option", {
          value: ""
        }), values.map((v, index) => {
          return o$2("option", {
            value: v.value,
            children: v.label
          });
        })]
      }), o$2(Description, {
        description: description
      }), o$2(Errors, {
        errors: errors
      })]
    });
  }

  SelectRenderer.create = function (options = {}) {
    const id = generateIdForType(type$4);
    return {
      id,
      key: id,
      label: this.label,
      type: type$4,
      values: [{
        label: 'Value',
        value: 'value'
      }],
      ...options
    };
  };

  SelectRenderer.type = type$4;
  SelectRenderer.label = 'Select';

  const type$5 = 'text';
  function TextRenderer(props) {
    const {
      field
    } = props;
    const {
      text
    } = field;
    return o$2("div", {
      class: formFieldClasses(type$5),
      children: o$2(Markup, {
        markup: safeMarkdown(text)
      })
    });
  }

  TextRenderer.create = function (options = {}) {
    const id = generateIdForType(type$5);
    return {
      id,
      text: '# Text',
      type: type$5,
      ...options
    };
  };

  TextRenderer.type = type$5;

  const type$6 = 'textfield';
  function TextfieldRenderer(props) {
    const {
      disabled,
      errors = [],
      field,
      path,
      value = ''
    } = props;
    const {
      description,
      id,
      label,
      validate = {}
    } = field;
    const {
      required
    } = validate;

    const onChange = ({
      target
    }) => {
      props.onChange({
        path,
        value: target.value.length ? target.value : undefined
      });
    };

    return o$2("div", {
      class: formFieldClasses(type$6, errors),
      children: [o$2(Label, {
        id: prefixId(id),
        label: label,
        required: required
      }), o$2("input", {
        class: "fjs-input",
        disabled: disabled,
        id: prefixId(id),
        onInput: onChange,
        type: "text",
        value: value
      }), o$2(Description, {
        description: description
      }), o$2(Errors, {
        errors: errors
      })]
    });
  }

  TextfieldRenderer.create = function (options = {}) {
    const id = generateIdForType(type$6);
    return {
      id,
      key: id,
      label: this.label,
      type: type$6,
      ...options
    };
  };

  TextfieldRenderer.type = type$6;
  TextfieldRenderer.label = 'Text Field';

  const fields = [ButtonRenderer, CheckboxRenderer, // ColumnsRenderer,
  DefaultRenderer, NumberRenderer, RadioRenderer, SelectRenderer, TextRenderer, TextfieldRenderer]; // export { createRenderer } from './CustomRenderer';

  function FormRenderer(options) {
    const {
      additionalRenderers = [],
      container,
      form
    } = options;
    const renderers = [...additionalRenderers, ...fields];

    const App = () => {
      const [state, setState] = l(form.getState());
      const formContext = {
        get fields() {
          return form.fields;
        },

        get data() {
          return state.data;
        },

        get errors() {
          return state.errors;
        },

        get properties() {
          return state.properties;
        },

        getFieldRenderer(type) {
          return findFieldRenderer(renderers, type);
        }

      };
      form.on('changed', newState => {
        setState(newState);
      });
      const onChange = A$1(update => form.update(update), [form]);
      const {
        properties
      } = state;
      const {
        readOnly
      } = properties;
      const onSubmit = A$1(() => {
        if (!readOnly) {
          form.submit();
        }
      }, [form, readOnly]);
      const onReset = A$1(() => form.reset(), [form]);
      return o$2("div", {
        class: "fjs-container",
        children: o$2(FormContext.Provider, {
          value: formContext,
          children: o$2(Form, {
            onChange: onChange,
            onSubmit: onSubmit,
            onReset: onReset,
            schema: state.schema
          })
        })
      });
    };

    N(o$2(App, {}), container);
  }

  const schemaVersion = 1;
  /**
   * @typedef { { container: Element; schema: any; data: any; properties?: any } } FormViewerOptions
   */

  /**
   * Create a form.
   *
   * @param {FormViewerOptions} options
   *
   * @return {FormCore}
   */

  function createForm(options) {
    const {
      container,
      schema,
      data,
      properties = {}
    } = options;
    const form = new FormCore({
      schema,
      data,
      properties
    });
    new FormRenderer({
      container,
      form
    });
    return form;
  }

  exports.createForm = createForm;
  exports.schemaVersion = schemaVersion;

  Object.defineProperty(exports, '__esModule', { value: true });

})));
