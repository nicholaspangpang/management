/*
    ********** In **********
    Project Home: http://injs.org

    Author: Guokai
    Gtalk: badkaikai@gmail.com
    Blog: http://benben.cc
    Licence: MIT License
    Version: 0.2.0-stable

    Philosophy: Just in time.
    Build: 110428120728
*/

~function() {
    var __head = document.head || document.getElementsByTagName('head')[0];
    var __waterfall = {};
    var __loaded = {};
    var __loading = {};
    var __globals = [];
    var __configure = {autoload: false, core: '', serial: false};
    var __in;
    
    // mapping for `In.load`
    // This method used for loading javascript or
    // style files asynchronous and non-blocking.

    var __load = function(url, type, charset, callback) {        
        if(__loading[url]) {
            if(callback) {
                setTimeout(function() {
                    __load(url, type, charset, callback);
                }, 1);
                return;
            }
            return;
        }
        
        if(__loaded[url]) {
            if(callback) {
                callback();
                return;
            }
            return;
        }
        
        __loading[url] = true;
        
        var pureurl = url.split(';')[0];
//        if(!pureurl[1]){
            //项目首次加载时用的“;”作为参数的
//            pureurl = url.split(';')[0];
//        };
        var n, t = type || pureurl.toLowerCase().substring(pureurl.lastIndexOf('.') + 1);
        
        if(t === 'js') {
            n = document.createElement('script');
            n.type = 'text/javascript';
            n.src = url;
            n.async = 'true';
            if(charset) {
                n.charset = charset;
            }
        } else if(t === 'css') {
            n = document.createElement('link');
            n.type = 'text/css';
            n.rel = 'stylesheet';
            n.href = url;
            __loaded[url] = true;
            __loading[url] = false;
            __head.appendChild(n);
            if(callback) callback();
            return;
        }

        n.onload = n.onreadystatechange = function() {
            if (!this.readyState || this.readyState === 'loaded' || this.readyState === 'complete') {
                __loading[url] = false;
                __loaded[url] = true;
                
                if(callback) {
                    callback();
                }
                
                n.onload = n.onreadystatechange = null;
            }
        };

        n.onerror = function() {
            __loading[url] = false;
            
            if(callback) {
                callback();
            }
            
            n.onerror = null;
        }
    
        __head.appendChild(n);
    };
    
    // private method, analyze the dependency.
    // This is the core function for dependency management.

    var __analyze = function(array) {
        var riverflow = [];

        for(var i = array.length-1; i >= 0; i--) {
            var current = array[i];

            if(typeof(current) === 'string') {
                if(!__waterfall[current]) {
                    console && console.warn && console.warn('In Error :: Module not found: ' + current);
                    continue;
                }

                riverflow.push(current);
                var relylist = __waterfall[current].rely;

                if(relylist) {
                    riverflow = riverflow.concat(__analyze(relylist));
                }
            } else if(typeof(current) === 'function') {
                riverflow.push(current);
            }
        }

        return riverflow;
    };
    
    // private method, serial process.
    // This method used for loading modules in serial.

    var __stackline = function(blahlist) {
        var o = this;

        this.stackline = blahlist;
        this.current = this.stackline[0];
        this.bag = {returns: [], complete: false};

        this.start = function() {
            if(typeof(o.current) != 'function' && __waterfall[o.current]) {
                __load(__waterfall[o.current].path, __waterfall[o.current].type, __waterfall[o.current].charset, o.next);
            } else {
                o.bag.returns.push(o.current());
                o.next();
            }
        };

        this.next = function() {
            if(o.stackline.length == 1 || o.stackline.length < 1) {
                o.bag.complete = true;
                if(o.bag.oncomplete) {
                    o.bag.oncomplete(o.bag.returns);
                }
                return;
            }

            o.stackline.shift();
            o.current = o.stackline[0];
            o.start();
        };
    };
    
    // private method, parallel process.
    // This method used for loading modules in parallel.

    var __parallel = function(blahlist, callback) {
        var length = blahlist.length;
        var hook = function() {
            if(!--length && callback) callback();
        };
        
        if(length == 0) {
            callback && callback();
            return;
        };
        
        for(var i = 0; i < blahlist.length; i++) {
            var current = __waterfall[blahlist[i]];
            
            if(typeof(blahlist[i]) == 'function') {
                blahlist[i]();
                hook();
                continue;
            }

            if(typeof(current) === 'undefined') {
                console && console.warn && console.warn('In Error :: Module not found: ' + blahlist[i]);
                hook();
                continue;
            }
            
            if(current.rely && current.rely.length != 0) {
                __parallel(current.rely, (function(current) {
                    return function() {
                        __load(current.path, current.type, current.charset, hook);
                    };
                })(current));
            } else {
                __load(current.path, current.type, current.charset, hook);
            }
        }
    };
    
    // mapping for `In.add`
    // This method used for adding module.

    var __add = function(name, config) {
        if(!name || !config || !config.path) return;
        __waterfall[name] = config;
    };
    
    // mapping for `In.adds`
    // This method used for adding modules.

    var __adds = function(config) {
        if(!config.modules) return;

        for(var module in config.modules) {
            if(config.modules.hasOwnProperty(module)) {
                var module_config = config.modules[module];

                if(!config.modules.hasOwnProperty(module)) continue;
                if(config.type && !module_config.type) module_config.type = config.type;
                if(config.charset && !module_config.charset) module_config.charset = config.charset;
                __add.call(this, module, module_config);
            }
        }
    };
    
    // mapping for `In.config`
    // This method used for change the default config.

    var __config = function(name, conf) {
        __configure[name] = conf;
    };
    
    // mapping for `In.css`
    // This method used for insert inline css to your page dynamically.

    var __css = function(csstext) {
        var css = document.getElementById('in-inline-css');
        
        if(!css) {
            css = document.createElement('style');
            css.type = 'text/css';
            css.id = 'in-inline-css';
            __head.appendChild(css);
        }
        
        if(css.styleSheet) {
            css.styleSheet.cssText = css.styleSheet.cssText + csstext;
        } else {
            css.appendChild(document.createTextNode(csstext));
        }
    };
    
    // mapping for `In.later`
    // This method used for loading modules delay time specified.

    var __later = function() {
        var args = [].slice.call(arguments);
        var timeout = args.shift();

        window.setTimeout(function() {
            __in.apply(this, args);
        }, timeout);
    };
    
    // mapping for `In.ready`
    // This method used for loading modules while domready.

    var __ready = function() {
        var args = arguments;

        __contentLoaded(window, function() {
            __in.apply(this, args);
        });
    };

    var __global = function() {
        var args = arguments[0].constructor === Array ? arguments[0] : [].slice.call(arguments);

        __globals = __globals.concat(args);
    };
    
    // mapping for `In`
    // This is the main function, also mapping for method `use`.

    var __in = function() {
        var args = [].slice.call(arguments);
        
        if(__globals.length) {
            args = __globals.concat(args);
        }

        if(__configure.serial) {
            if(__configure.core && !__loaded[__configure.core]) {
                args = ['__core'].concat(args);
            }

            var blahlist = __analyze(args).reverse();
            var stack = new __stackline(blahlist);

            stack.start();
            return stack.bag;
        }
        
        if(typeof(args[args.length-1]) === 'function') {
            var callback = args.pop();
        }
        
        if(__configure.core && !__loaded[__configure.core]) {
            __parallel(['__core'], function() {
                __parallel(args, callback);
            });
        } else {
            __parallel(args, callback);
        }
    };
    
    // private method, contentLoaded.
    // This method used for domready.

    var __contentLoaded = function(win,fn) {
        var done = false, top=true,
            doc = win.document, root = doc.documentElement,
            add = doc.addEventListener ? 'addEventListener' : 'attachEvent',
            rem = doc.addEventListener ? 'removeEventListener' : 'detachEvent',
            pre = doc.addEventListener ? '' : 'on',
            
            init = function(e) {
                if(e.type == 'readystatechange' && doc.readyState != 'complete') return;
                (e.type == 'load' ? win : doc)[rem](pre + e.type, init, false);
                if(!done && (done=true)) fn.call(win, e.type || e);
            },
            
            poll = function() {
                try {root.doScroll('left');} catch(e) {setTimeout(poll, 50);return;}
                init('poll');
            };
        
        if(doc.readyState == 'complete') {
            fn.call(win, 'lazy');
        } else {
            if(doc.createEventObject && root.doScroll) {
                try {top =! win.frameElement;} catch(e) {}
                if(top) poll();
            }

            doc[add](pre + 'DOMContentLoaded', init, false);
            doc[add](pre + 'readystatechange', init, false);
            win[add](pre + 'load', init, false);
        }
    }
    
    // private method, initialize.
    // This is a self-executing function while in.js loaded.

    void function() {
        var myself = (function() {
            var scripts = document.getElementsByTagName('script');
            return scripts[scripts.length - 1];
        })();

        var autoload = myself.getAttribute('autoload');
        var core = myself.getAttribute('core');
        
        if(core) {
            __configure['autoload'] = eval(autoload);
            __configure['core'] = core;
            __add('__core', {path: __configure.core});
        }
        
        // autoload the core files
        if(__configure.autoload && __configure.core) {
            __in();
        }
    }();
    
    // Bind the private method to in.

    __in.add = __add;
    __in.adds = __adds;
    __in.config = __config;
    __in.css = __css;
    __in.later = __later;
    __in.load = __load;
    __in.ready = __ready;
    __in.global = __global;
    __in.use = __in;

    this.In = __in;
}();
