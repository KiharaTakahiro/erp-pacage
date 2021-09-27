export default {
  route: {
    dashboard: 'トップ',
    sales: '販売管理',
    recivedOrder: '受注管理',
    quotation: '見積管理',
    newRecivedOrder: '受注表作成',
    editRecivedOrder: '受注票編集',
    recivedOrderList: '受注一覧',
    OrderDetail: '注文詳細',
    invoice: '請求管理',
    system: 'システム設定',
    users: 'ユーザ管理',
    createUsre:"ユーザー登録",
    userList:"ユーザー一覧",
    client: '取引先管理',
    newClient: '取引先追加',
    clientList: '取引先一覧',
    editClient: '取引先編集',
    supplierProduct: '仕入商品管理',
    supplierProductList: '仕入商品一覧',
    saveSupplierProduct: '仕入商品追加',
    product: '商品管理',
    productList: '商品一覧',
    newProduct: '商品追加',
    editProduct: '商品編集',
    documentation: 'ドキュメント',
    guide: 'ガイド',
    permission: '権限',
    rolePermission: '権限ロール',
    pagePermission: 'ページ権限',
    directivePermission: 'ディレクティブ権限',
    icons: 'アイコン',
    components: 'コンポーネント',
    tinymce: 'TinyMCE',
    markdown: 'Markdown',
    jsonEditor: 'JSON Editor',
    splitPane: 'パネル',
    avatarUpload: 'アバターアップロード',
    dropzone: 'Dropzone',
    sticky: 'Sticky',
    countTo: 'Count To',
    componentMixin: 'コンポーネントMixin',
    backToTop: 'Back To Top',
    draggableDialog: 'Draggable Dialog',
    draggableKanban: 'Draggable 看板',
    draggableList: 'Draggable List',
    draggableSelect: 'Draggable Select',
    charts: 'チャート',
    barChart: 'Barチャート',
    lineChart: 'Lineチャート',
    mixedChart: 'Mixedチャート',
    example: 'Example',
    nested: 'Nested Routes',
    menu1: 'メニュー１',
    'menu1-1': 'メニュー 1-1',
    'menu1-2': 'メニュー 1-2',
    'menu1-2-1': 'メニュー 1-2-1',
    'menu1-2-2': 'メニュー 1-2-2',
    'menu1-3': 'メニュー 1-3',
    menu2: 'メニュー 2',
    table: 'Table',
    dynamicTable: '可変 Table',
    draggableTable: 'Draggable Table',
    inlineEditTable: 'Inline Edit Table',
    complexTable: 'Complex Table',
    tab: 'Tab',
    form: 'フォーム',
    createArticle: '投稿作成',
    editArticle: '投稿編集',
    articleList: '投稿リスト',
    errorPages: 'エラーページ',
    page401: '401',
    page404: '404',
    errorLog: 'エラーログ',
    excel: 'Excel',
    exportExcel: '一括エクスポート',
    selectExcel: '複数選択エクスポート',
    mergeHeader: 'ヘッダーマージ',
    uploadExcel: 'アップロード',
    zip: 'Zip',
    pdf: 'PDF',
    exportZip: 'Export Zip',
    theme: 'テーマ変更',
    clipboard: 'Clipboard',
    i18n: '多言語',
    externalLink: '外部リンク',
    profile: 'プロフィール',
    back :'戻る',
    search : '検索',
    reset : 'リセット',
    edit: "編集"
  },
  navbar: {
    dashboard: 'トップ',
    github: 'GitHub',
    logOut: 'ログアウト',
    profile: 'プロフィール',
    theme: 'テーマ変更',
    size: '画面サイズ'
  },
  login: {
    title: 'ユーザログイン',
    logIn: 'ログイン',
    username: 'ユーザ名',
    password: 'パスワード',
    any: 'any',
    thirdparty: '外部IDでログイン',
    thirdpartyTips: 'ローカル環境ではログインできません。実装が必要です。'
  },
  documentation: {
    documentation: 'ドキュメント',
    github: 'Github Link'
  },
  user:
  {
    add :'ユーザー新規登録',
    firstName: '名前',
    lastName: '名字',
    id: 'ユーザーID',
    email: 'メールアドレス',
    password: 'パスワード',
    password2: 'パスワード（確認用）',
    complete: '完了',
  },
  client: {
    title: '取引先管理',
    add: '取引先新規登録',
    edit: '取引先編集',
    complete: '完了',
    name: '会社名',
    check0: '会社が選択されていません',
    check2: '会社が二つ以上選択されています'
  },
  recivedOrder: {
    add: '受注表作成',
    complete: '完了',
    check0: '受注票が選択されていません',
    check2: '会受注票が二つ以上選択されています'
  },
  supplierProduct: {
    title: '仕入商品管理',
    add: '仕入商品新規登録',
    edit: '仕入商品編集',
    complete: '完了',
    name: '商品名',
    check0: '商品が選択されていません',
    check2: '商品が二つ以上選択されています'
  },
  product: {
    title: '商品管理',
    add: '商品新規登録',
    edit: '商品編集',
    complete: '完了',
    id: "商品Seq",
    name: '商品名',
    unitPrice: '定価',
    unitPriceFrom: '定価From',
    unitPriceTo: '定価To',
    taxType: '税区分',
    purchaseUnitPrice: '原価',
    PurchaseunitPriceFrom: '原価From',
    PurchaseunitPriceTo: '原価To',
    check0: '商品が選択されていません',
    check2: '商品が二つ以上選択されています'
  },
  permission: {
    createRole: 'ロール追加',
    editPermission: 'ロール変更',
    roles: 'ロール',
    switchRoles: 'ロール切替',
    tips: 'v-permissionは使えない時があります。例えば: Element-UI の el-tab、 el-table-column 及び他の dom。v-ifを使う必要があります。',
    delete: '削除',
    confirm: '確認',
    cancel: 'キャンセル'
  },
  guide: {
    description: 'ガイドは各機能の説明です。',
    button: 'ガイドを見る'
  },
  components: {
    documentation: 'ドキュメント',
    tinymceTips: 'tinymceは管理画面に重要な機能ですが、その同時に落とし穴がありあす。tinymceを使う道のりが大変でした。Tinymceを使う時に各自のプロジェクト状況で判断が必要です。ドキュメントはこちら',
    stickyTips: 'ページの指定位置へスクロールした場合、表示されます。',
    backToTopTips1: 'トップへスクロールが表示されます。',
    backToTopTips2: 'ボタンのスタイルはカスタマイズできます。例えば、show/hide、height、position。 またはElementのel-tooltipを使って、ツールチップを実装できます。',
    imageUploadTips: 'mockjsは使えないため、カスタマイズしています。公式の最新バージョンを使ってください。',
    changeLanguageTips: '言語切り替えの成功',
    createClients :'取引先の登録に成功',
    createRecive: '受注票の作成に成功',
    createProduct :'商品の登録に成功',
    validation :'入力内容を確認してください'
  },
  table: {
    dynamicTips1: '先頭は固定、最後に追加',
    dynamicTips2: '戦後に追加せず、指定列に追加',
    dragTips1: 'デフォルト順番',
    dragTips2: 'Drag後の順番',
    title: 'タイトル',
    importance: '重要',
    type: 'タイプ',
    remark: '評価',
    search: '検索',
    add: '追加',
    export: 'エクスポート',
    reviewer: 'レビュアー',
    id: '番号',
    date: '日時',
    author: '作成者',
    readings: '閲覧数',
    status: 'ステータス',
    actions: '操作',
    edit: '編集',
    publish: '公開',
    draft: '下書き',
    delete: 'キャンセル',
    cancel: 'キャンセル',
    confirm: '確認'
  },
  example: {
    warning: '新規作成と編集画面は keep-alive を使えないです。keep-alive の include はrouteのキャッシュは使えないです。そのため、component name を使ってキャッシュさせるようにします。このようなキャッシュ機能を作りたい場合，localStorageを使う手があります。もしくは keep-alive の includeを使って、全ページキャッシュする方法はあります。'
  },
  errorLog: {
    tips: '右上のbugアイコンをクリックしてください。',
    description: '管理画面はspaを使う場合が多い、ユーザ体現向上はできますが、想定外エラーが発生する場合があります。Vueはそのエラーハンドリング機能を提供し、エラーレポートができます。',
    documentation: 'ドキュメント'
  },
  excel: {
    export: 'エクスポート',
    selectedExport: 'エクスポート対象を選択してください。',
    placeholder: 'ファイル名を入力してください。'
  },
  zip: {
    export: 'エクスポート',
    placeholder: 'ファイル名を入力してください。'
  },
  pdf: {
    tips: 'window.print() を使ってPDFダウンロードしています。'
  },
  theme: {
    change: 'テーマ切替',
    documentation: 'ドキュメント',
    tips: 'Tips: テーマの切り替え方法はnavbarのtheme-pickと異なります、使い方はドキュメントを確認してください。'
  },
  tagsView: {
    refresh: '更新',
    close: '閉じる',
    closeOthers: 'その他閉じる',
    closeAll: 'すべて閉じる'
  },
  settings: {
    title: 'システムテーマ',
    theme: 'テーマ色',
    showTagsView: 'Tags-View 開く',
    showSidebarLogo: 'Show Sidebar Logo',
    fixedHeader: 'Fixed Header',
    sidebarTextTheme: 'Sidebar Text Theme'
  }
}
