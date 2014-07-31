Ext.define('estools.view.applicationlayout.MenuScreen', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.applicationmenuscreen',
    border: false,
    items: [{
            title: 'Settings', layout: {
                align: 'middle',
                pack: 'center',
                type: 'hbox'
            },
            items: [
                {
                    xtype: 'button',
                    text: 'Logout',
                    itemId: 'userLogout',
                    action: 'logout'
                }
            ],
            bodyPadding: 5
        }],
    layout: 'accordion',
    title: 'Navigation',
    collapsible: true,
    split: true

});
