# Ubuntu 20.04での開発環境のセットアップ

#### SDKMANのインストール

以下のコマンド実行してSDKMANをインストールします。

```bash
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
```

#### JDK 11のインストール

以下のコマンドを実行して、AdoptOpenJDK JDK 11をインストールします。

```bash
sdk install java $(sdk list java | grep -o "11\.[0-9]*\.[0-9]*\.hs-adpt" | head -1)
```

#### sbtのインストール

以下のコマンドを実行して、sbtをインストールします。

```bash
sdk install sbt
```

#### IntelliJ IDEAのインストール

IntelliJ IDEAの[ダウンロードページ](https://www.jetbrains.com/ja-jp/idea/download/#section=linux)からダウンロードしてインストールします。(Community版でも開発に支障はありません。)

ダウンロード後、以下のコマンドを実行します。「2021.2」の部分は変化します。

```bash
cd ~/ダウンロード
sudo tar -xzvf ideaIC-2021.2.tar.gz -C /opt
```

実行後、以下のコマンドを実行します。

```bash
echo "export PATH=/opt/idea-IC-212.4746.92/bin:$PATH" >> ~/.bashrc
source ~/.bashrc
```

IntelliJ IDEAの起動は以下のコマンドを実行します。

```bash
idea.sh
```

#### Vivadoのインストール

Vivadoの[ダウンロードページ](https://japan.xilinx.com/support/download/index.html/content/xilinx/ja/downloadNav/vivado-design-tools/2020-2.html)で「ザイリンクス統合インストーラー 2020.2: Linux 用自己解凍型ウェブ インストーラー」をクリックしてダウンロードインストーラをダウンロードします。(Xilinxのアカウントを作成していない場合は、「アカウント」をクリックしてアカウントを作成します。)

ダウンロード後、以下のコマンドを実行します。

```bash
cd ~/ダウンロード
chmod +x Xilinx_Unified_2020.2_1118_1232_Lin64.bin
sudo ./Xilinx_Unified_2020.2_1118_1232_Lin64.bin
```

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
(デフォルトのインストール先は/tools/Xilinxです。)

インストール後に、ケーブルドライバをインストールします。

```bash
cd tools/Xilinx/Vivado/2020.2/data/xicom/cable_drivers/lin64/install_script/install_drivers
sudo ./install_drivers
```

以下のコマンドを実行してシリアルデバイスの読み書き権限の設定をします。

```bash
sudo adduser $USER dialout
```

このままでは、Vivadoは起動しないので、以下のコマンドを実行します。

```bash
sudo apt install libtinfo-dev
sudo ln -s /lib/x86_64-linux-gnu/libtinfo.so.6 /lib/x86_64-linux-gnu/libtinfo.so.5
```

Vivadoの起動には以下のコマンドを実行します。

```bash
source /tools/Xilinx/Vivado/2020.2/settings64.sh
vivado
```

起動のために、毎回2つのコマンドを実行するのが面倒な場合は以下のコマンドを実行して、起動スクリプトを作成します。
(`settings64.sh`の内容はお行儀がかなり悪い事が多いので、~/.bashrc等に追加することはお勧め **しません** 。)

```bash
sudo sh -c 'cat <<END >/usr/local/bin/vivado.sh
source /tools/Xilinx/Vivado/2020.2/settings64.sh
vivado
END'
sudo chmod +x /usr/local/bin/vivado.sh
```

起動スクリプトからの起動は以下のようにします。

```bash
vivado.sh
```

#### FPGAボード・ファイルのインストール

Cmod A7のボートに関する定義ファイルがDigilent社より出ているので、 [ダウンロード](https://github.com/Digilent/vivado-boards/archive/master.zip) して、Vivadoのボード定義ファイルのディレクトリにコピーします。

```bash
cd ~/ダウンロード/
unzip vivado-boards-master.zip
cd vivado-boards-master/new/board_files/
sudo cp -r * /tools/Xilinx/Vivado/2020.2/data/boards/board_files/
```

#### FPGAの制約ファイル・テンプレートのダウンロード

自分で開発したFPGAの回路構成とFPGAチップの入出力端子を対応付けるために、制約ファイル(constraint file)というものが必要になります。
このファイルの雛形が、DIGILENT社より[GitHub](https://github.com/Digilent/digilent-xdc/)で提供されていますので、 `Cmod-A7-Master.xdc` のファイルをダウンロードしておきます。
