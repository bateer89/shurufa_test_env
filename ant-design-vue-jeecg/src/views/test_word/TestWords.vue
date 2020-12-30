<template>
  <a-card :bordered="false">
  
  <input type="text" placeholder="输入" v-model="inputVal"  v-on:input="check()" value="" />
<input type="text" placeholder="请输入" @keyup="show($event)">
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  // import ZnWordsModal from './modules/ZnWordsModal'
  import JSuperQuery from '@/components/jeecg/JSuperQuery.vue'
   import Vue from 'vue' 
  // Vue.use(Element)

  export default {
    name: 'TestWords',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      // ZnWordsModal,
      // JSuperQuery,
    },
    data () {
      return {
        description: '汉字对照表管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          }
        ],
        url: {
          list: "/words/znWords/list",
          delete: "/words/znWords/delete",
          deleteBatch: "/words/znWords/deleteBatch",
          exportXlsUrl: "/words/znWords/exportXls",
          importExcelUrl: "words/znWords/importExcel",
          
        },
        dictOptions:{},
        superFieldList:[],
        inputVal:"",
      }
    },
    created() {
    },
    computed: {
      // importExcelUrl: function(){
      //   return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      // },
    },
    methods: {
      check(){
        console.log(this.inputVal,"okkkkkkkkkkkkk");
      },
      show($event){
        console.log($event,"okkkkkkkkkkkkk");
      }
      // initDictConfig(){
      // },
      // getSuperFieldList(){
      //   let fieldList=[];
      //   fieldList.push({type:'string',value:'words',text:'汉字',dictCode:''})
      //   fieldList.push({type:'string',value:'keyboardSequence',text:'键盘序',dictCode:''})
      //   fieldList.push({type:'string',value:'code1',text:'code1',dictCode:''})
      //   fieldList.push({type:'string',value:'code2',text:'code2',dictCode:''})
      //   fieldList.push({type:'string',value:'code3',text:'code3',dictCode:''})
      //   fieldList.push({type:'int',value:'ifAllPassed',text:'是否正确0:no;1:yes',dictCode:''})
      //   fieldList.push({type:'int',value:'ifChecked',text:'是否检查过',dictCode:''})
      //   fieldList.push({type:'string',value:'checkerid',text:'检查者id',dictCode:''})
      //   fieldList.push({type:'string',value:'checkername',text:'检查者name',dictCode:''})
      //   this.superFieldList = fieldList
      // }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>