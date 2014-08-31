Ext.define('estools.model.ProjectFeatures', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'projectId', type: 'int'},
        {name: 'type', type: 'int'},
        {name: 'title', type: 'string'},
        {name: 'task', type: 'string'},
        {name: 'name', type: 'string'},
        {name: 'scoped', type: 'string'},
        {name: 'created', type: 'date'},
        {name: 'updated', type: 'date'}
    ],
    proxy: {
        type: 'ajax',
        url: '',
        reader: {
            type: 'json',
            root: 'results'
        }
    }
});

