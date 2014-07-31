Ext.Loader.setConfig({
    enabled: true
});
Ext.Loader.setPath('Ext.ux', 'js/extjs/ux');
Ext.QuickTips.init();
Ext.application({
    name: 'estools',
    appFolder: 'js/app',
    // Required classes
    requires: [
        'Ext.ux.form.MultiSelect',
        'Ext.ux.form.ItemSelector',
        'estools.view.Viewport',
        'estools.view.applicationlayout.Header',
        'estools.view.applicationlayout.MainScreen',
        'estools.view.logon.LogonWindow',
        'estools.view.logon.LogonFormPanel',
        'estools.view.logon.SignupFormPanel',
        'estools.view.logon.ProfileSignupFormPanel',
        'estools.view.users.Grid',
        'estools.view.users.DetailPanel',
        'estools.view.users.MasterPanel',
        'estools.view.users.Edit',
        'estools.view.devgroup.Grid',
        'estools.view.devgroup.DetailPanel',
        'estools.view.devgroup.MasterPanel',
        'estools.view.devgroup.Edit',
        'estools.view.project.Grid',
        'estools.view.project.DetailPanel',
        'estools.view.project.MasterPanel',
        'estools.view.project.Edit'
    ],
    // Required controllers
    controllers: [
        'Logon', 'User', 'DevGroup', 'Project'
    ],
    models: [
        'User', 'DevGroup', 'Project'
    ],
    stores: [
        'User', 'DevGroup', 'Project'
    ],
    // Stuff to do at launch
    launch: function() {
        estools.utils.reloadFromCacheIfExists();
        if (globalvar.currentUserId && globalvar.currentUserId > 0) {
            Ext.create('estools.view.Viewport');
        } else {
            Ext.create('estools.view.logon.LogonWindow').show();
        }
    },
    // Application initialization stuff
    init: function() {
        // Attach listener to application wide events
        this.control({
        });
    }
});