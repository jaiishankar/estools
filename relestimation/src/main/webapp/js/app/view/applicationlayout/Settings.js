Ext.define('estools.view.applicationlayout.Settings', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.usersettings',
    border: false,
    title: 'Settings',
    initComponent: function() {
        this.tbar = [
            {
                xtype:'button',
                text:'logout',
                action: 'logout'
            }
        ];
        
        this.callParent();
    }
});