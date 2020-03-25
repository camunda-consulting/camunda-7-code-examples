/*----------------------------------------------------*/
/*	Isotope Portfolio Filter Section
 /*----------------------------------------------------*/

jQuery(function () {

    var jQuerycontainer = jQuery('.portfolio');

    jQuerycontainer.imagesLoaded(function(){
        jQuerycontainer.isotope({
            itemSelector: '.item'
        });
    });



    var jQueryoptionSets = jQuery('#options .option-set'),
        jQueryoptionLinks = jQueryoptionSets.find('a');

    jQueryoptionLinks.click(function () {
        var jQuerythis = jQuery(this);
        // don't proceed if already selected
        if (jQuerythis.hasClass('selected')) {
            return false;
        }
        var jQueryoptionSet = jQuerythis.parents('.option-set');
        jQueryoptionSet.find('.selected').removeClass('selected');
        jQuerythis.addClass('selected');

        // make option object dynamically, i.e. { filter: '.my-filter-class' }
        var options = {},
            key = jQueryoptionSet.attr('data-option-key'),
            value = jQuerythis.attr('data-option-value');
        // parse 'false' as false boolean
        value = value === 'false' ? false : value;
        options[key] = value;
        if (key === 'layoutMode' && typeof changeLayoutMode === 'function') {
            // changes in layout modes need extra logic
            changeLayoutMode(jQuerythis, options)
        } else {
            // otherwise, apply new options
            jQuerycontainer.isotope(options);
        }

        return false;
    });


});