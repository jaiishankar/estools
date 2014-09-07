Ext.define('estools.model.ProjectFeatureSizing', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'sizingId', type: 'int'},
        {name: 'userId', type: 'int'},
        {name: 'groupId', type: 'int'},
        {name: 'featureId', type: 'int'},
        {name: 'projectId', type: 'int'},
        {name: 'created', type: 'date'},
        {name: 'updated', type: 'date'}
    ],
    proxy: {
        type: 'ajax',
        reader: {
            type: 'json',
            root: 'results'
        }
    }
});