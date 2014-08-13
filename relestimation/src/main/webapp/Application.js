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
        'estools.view.project.Edit',
        'estools.view.project.metrics.Grid',
        'estools.view.sizing.Grid',
        'estools.view.sizing.DetailPanel',
        'estools.view.sizing.MasterPanel',
        'estools.view.sizing.Edit',
        'estools.view.priority.Grid',
        'estools.view.priority.DetailPanel',
        'estools.view.priority.MasterPanel',
        'estools.view.priority.Edit',
        'estools.view.assumptiontype.Grid',
        'estools.view.assumptiontype.DetailPanel',
        'estools.view.assumptiontype.MasterPanel',
        'estools.view.assumptiontype.Edit'
    ],
    // Required controllers
    controllers: [
        'Logon', 'User', 'DevGroup', 'Project','Sizing'
    ],
    models: [
        'User', 'DevGroup', 'Project', 'Sizing', 'Priority','AssumptionType','ProjectMetrics'
    ],
    stores: [
        'User', 'DevGroup', 'Project', 'Sizing', 'Priority','AssumptionType','ProjectMetrics'
    ],
    // Stuff to do at launch
    launch: function() {
        estools.utils.reloadFromCacheIfExists();
        if (globalvar.currentUserId && globalvar.currentUserId > 0) {
            //we need to see if still that user is present in the DB and if not
            // clear the local cache and re-route to login page.
            this.validateAndShowApplicationPage(globalvar.currentUserId);
        } else {
            this.showLogonWindow();
        }
    },
    // Application initialization stuff
    init: function() {
        // Attach listener to application wide events
        this.control({
        });
    },
    validateAndShowApplicationPage: function(userId) {
        Ext.Ajax.request({
            method: 'GET',
            url: './v1/users/' + userId,
            headers: {
                'Accept': 'application/json'
            },
            scope: this,
            success: function(response, options) {
                var responseData = Ext.decode(response.responseText);
                if (responseData.success) {
                    Ext.create('estools.view.Viewport');
                }
                else {
                    this.showLogonWindow();
                }
            }
        });
    },
    showLogonWindow: function() {
        estools.utils.clearCache();
        Ext.create('estools.view.logon.LogonWindow').show();
    }
});