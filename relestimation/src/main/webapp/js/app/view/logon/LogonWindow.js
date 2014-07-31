Ext.define('estools.view.logon.LogonWindow', {
    extend: 'Ext.window.Window',
    alias: 'widget.logonwindow',
    title: 'Logon',   
    layout: 'card',
    closable: false,
    width: 400,
    height: 300,   
    border: 1,
    
    initComponent: function() {

        // set the login form into the window
        this.items = [
            {xtype: 'logonformpanel'},
            {xtype: 'signupformpanel'},
            {xtype: 'profilesignupformpanel'}
        ];        
        
        // call the superclass's initComponent implementation
        this.callParent();
    }    
});