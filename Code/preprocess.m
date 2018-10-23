 data = csvread("nowacst.csv");
 col2 = data(:, 2);
 [f, x] = ecdf(col2);

 f = f(2:end);
 x = x(2:end);
 fid = fopen("Data.txt", 'w');
 fprintf(fid, '%f %f \n', [f x]');
 fclose(fid);
 