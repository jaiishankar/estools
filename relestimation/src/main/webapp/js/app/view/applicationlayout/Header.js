Ext.define('estools.view.applicationlayout.Header', {
    extend: 'Ext.Container',
    alias: 'widget.applicationheader',
    border: false,
    initComponent: function() {
        this.items = [
            {
                html: '<center><img src="./images/project_title_img.png" height=100/></center>',
                margins: '0 0 5 0',
                flex: 1
            }
        ];
        // call the superclass's initComponent implementation
        this.callParent();
    }
});