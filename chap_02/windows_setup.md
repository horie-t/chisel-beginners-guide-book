### Windows 10での開発環境のセットアップ

#### JDK 11のインストール

[AdoptOpenJDK](https://adoptopenjdk.net/)のサイトから、バージョン OpenJDK 11(LTS)、JVM HotSpotの最新リリースをダウンロードします。ダウンロードしたファイルを実行してインストールします。

#### sbtのインストール

sbtの[インストール方法](https://www.scala-sbt.org/1.x/docs/ja/Installing-sbt-on-Windows.html)のページからWindowsのmsiインストーラをダウンロードしてインストールします。

#### IntelliJ IDEAのインストール

IntelliJ IDEAの[ダウンロードページ](https://www.jetbrains.com/ja-jp/idea/download/#section=windows)からダウンロードしてインストールします。(Community版でも開発に支障はありません。)

#### Vivadoのインストール

Vivadoの[ダウンロードページ](https://japan.xilinx.com/support/download/index.html/content/xilinx/ja/downloadNav/vivado-design-tools/2020-2.html)で「ザイリンクス統合インストーラー 2020.2: Windows 用自己解凍型ウェブ インストーラー」をクリックしてダウンロードインストーラをダウンロードします。(Xilinxのアカウントを作成していない場合は、「アカウント」をクリックしてアカウントを作成します。)

ダウンロードが完了したら、ダウンロードしたファイルを実行してインストールを開始します。

インストーラの画面が起動後、以下の手順でインストールします。

1. Welcome画面で[Next]をクリック
2. Select Install Type画面で、登録したUser ID、Passwordを入力し、[Download and Install Now]を選択し、[Next]をクリック
3. Select Product to Install画面で、Vivadoを選択し、[Next]をクリック
4. Select Edition to Installで、[Vivado WebPACK]を選択し、[Next]をクリック
5. Vivado HL WebPACK画面はデフォルトのまま、[Next]をクリック
6. Accept Licence Agreement画面で、すべてのチェック・ボックスにチェックをして、[Next]をクリック
7. Select Destination Directory画面でインストール先を選択して、[Next]をクリック
8. Install Summary画面で、インストール内容を確認し、[Install]をクリック

して、インストールを開始します。(インストールには、かなり時間がかかります)

インストール後に、ケーブルドライバをインストールします。「Windowsキー + s」を押下し「dmd」と入力して、「コマンドプロンプト」を右クリックし、「管理者として実行」をクリックします。コマンドプロンプトで以下を入力します。

```
cd C:\Xilinx\Vivado\2020.2\data\xicom\cable_drivers\nt64
install_drivers_wrapper.bat
```

#### FPGAボード・ファイルのインストール

Cmod A7のボートに関する定義ファイルがDigilent社より出ているので、 [ダウンロード](https://github.com/Digilent/vivado-boards/archive/master.zip) して、Vivadoのボード定義ファイルのディレクトリにコピーします。

zipファイルを展開後  `vivado-boards-master\new\board_files` フォルダのすべてを `C:\Xilinx\Vivado\2020.2\data\boards\board_files` にコピーします。

#### FPGAの制約ファイル・テンプレートのダウンロード

自分で開発したFPGAの回路構成とFPGAチップの入出力端子を対応付けるために、制約ファイル(constraint file)というものが必要になります。
このファイルの雛形が、DIGILENT社より[GitHub](https://github.com/Digilent/digilent-xdc/)で提供されていますので、 `Cmod-A7-Master.xdc` のファイルをダウンロードしておきます。
