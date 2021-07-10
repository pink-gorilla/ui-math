window.MathJax = {
  tex: {
    inlineMath: [["@@", "@@"],
    ['$', '$'],
    ['\\(', '\\)']]
  },
  svg: {
    fontCache: 'global'
  }
};

function mathinit(cb) {
  
  window.MathJax.startup = {
      ready: () => {
        console.log('MathJax is loaded, but not yet initialized');
        MathJax.startup.defaultReady();
        console.log('MathJax is initialized, and the initial typeset is queued');
        cb();
      }
    };
  
  var url = '/r/mathjax/tex-svg-full.js';
  //var url = 'https://cdn.jsdelivr.net/npm/mathjax@3/es5/tex-svg.js';
  console.log("loading mathjax from: " + url)
  var script = document.createElement('script');
  script.src = url
  script.async = true;
  document.head.appendChild(script);
  console.log(" mathjax script added.")
};


module.exports.mathinit = mathinit;
