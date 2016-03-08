/** 
 * @author Marek Pierscieniak
 * @date 08 march 2016
 */
package Helpers;

import java.io.File;
import javax.swing.filechooser.FileFilter;
/**
 * ExtensionFilter class. Used in LoadPicture and SavePicture to filter file types.
 */
public class ExtensionFilter extends FileFilter
{
    private String extension;
    private String description;

    public ExtensionFilter(String extension, String description)
    {
        this.extension = extension;
        this.description = description;
    }

    public boolean accept(File file)
    {
        return file.isDirectory() || file.getName().endsWith(extension);
    }

    public String getDescription()
    {
        return description;
    }

    public String getExtension()
    {
        return extension;
    }
}