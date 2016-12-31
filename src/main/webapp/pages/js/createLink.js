 function createLink(form)
    {
        var link = form.action+"?"
        for (var l = 0; l<form.elements.length; l++)
        {
            link+=form.elements[l].name+"="+form.elements[l].value
            if (l!=form.elements.length-1) link+="&"
        }
        window.open("tradeform",'subwindow','HEIGHT=500,WIDTH=500')
    }