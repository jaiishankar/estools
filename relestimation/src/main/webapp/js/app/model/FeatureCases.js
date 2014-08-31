Ext.define('estools.model.FeatureCases', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'id', type: 'int'},
        {name: 'featureId', type: 'int'},
        {name: 'priorityId', type: 'int'},
        {name: 'note', type: 'string'},
        {name: 'question', type: 'string'},
        {name: 'taskDetails', type: 'string'},
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