Ext.define('estools.model.ProjectFeatureSizing', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'sizingId', type: 'string'},
        {name: 'userId', type: 'string'},
        {name: 'groupId', type: 'string'},
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